package com.example.data

import com.example.data.local.source.JobsCacheDataSource
import com.example.data.remote.source.RemoteDataSource
import com.example.domain.entities.AndroidJob
import com.example.domain.repository.AndroidJobsRepository
import io.reactivex.Single

class AndroidJobsRepositoryImpl(
    private val jobsCacheDataSource: JobsCacheDataSource,
    private val remoteDataSource: RemoteDataSource
): AndroidJobsRepository {

    override fun getJobs(forceUpdate: Boolean): Single<List<AndroidJob>> {
        return if (forceUpdate)
            getJobsRemote(forceUpdate)
        else
            jobsCacheDataSource.getJobs()
            .flatMap { listJobs ->
                when{
                    listJobs.isEmpty() -> getJobsRemote(false)
                    else -> Single.just(listJobs)
                }
            }
    }

    private fun getJobsRemote(isUpdate: Boolean): Single<List<AndroidJob>> {
        return remoteDataSource.getJobs()
            .flatMap { listJobs ->
                if (isUpdate)
                    jobsCacheDataSource.updateData(listJobs)
                else
                    jobsCacheDataSource.insertData(listJobs)
                Single.just(listJobs)
            }
    }
}