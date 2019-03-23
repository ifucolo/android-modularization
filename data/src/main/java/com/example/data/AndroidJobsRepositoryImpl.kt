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
        return jobsCacheDataSource.getJobs()
            .flatMap { listJobs ->
                when{
                    forceUpdate || listJobs.isEmpty() -> getJobsRemote()
                    else -> Single.just(listJobs)
                }
            }
    }

    private fun getJobsRemote(): Single<List<AndroidJob>> {
        return remoteDataSource.getJobs()
            .flatMap { listJobs ->
                listJobs.forEach { jobsCacheDataSource.insertJob(it) }
                Single.just(listJobs)
            }
    }
}