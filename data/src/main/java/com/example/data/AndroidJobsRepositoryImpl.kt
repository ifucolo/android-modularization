package com.example.data

import com.example.data.local.mapper.AndroidJobCacheMapper
import com.example.data.local.source.JobsCacheDataSource
import com.example.data.remote.mapper.AndroidJobMapper
import com.example.data.remote.source.RemoteDataSource
import com.example.domain.entities.AndroidJob
import com.example.domain.repository.AndroidJobsRepository
import com.example.domain.responses.ResultRemote
import com.example.domain.responses.ResultRequired
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AndroidJobsRepositoryImpl(
    private val jobsCacheDataSource: JobsCacheDataSource,
    private val remoteDataSource: RemoteDataSource
): AndroidJobsRepository {

    override fun getJobs(): Flow<ResultRequired<List<AndroidJob>>> {
        return jobsCacheDataSource.getJobs()
            .map { cacheList ->
                val result = when {
                    cacheList.isEmpty() -> getJobsRemote()
                    else -> {
                        val jobs = AndroidJobCacheMapper.map(cacheList)
                        ResultRequired.Success(jobs)
                    }
                }

                result
            }
    }

    override fun add() {
        val androidJob = AndroidJob(
            title = "flow",
            experienceTimeRequired = (0..500).random().toString(),
            native = true,
            country = "Braziil"
        )

        val cacheJob = AndroidJobCacheMapper.map(androidJob)
        jobsCacheDataSource.insertData(cacheJob)
    }

    private suspend fun getJobsRemote(): ResultRequired<List<AndroidJob>> {
        val resultRemote = remoteDataSource.getJobs()

        return when(resultRemote) {
            is ResultRemote.Success -> {
                val mappedList = AndroidJobMapper.map(resultRemote.response)
                val cacheList = AndroidJobCacheMapper.mapJobsToCache(mappedList)

                jobsCacheDataSource.updateData(cacheList)

                ResultRequired.Success(
                    result = mappedList
                )
            }
            is ResultRemote.ErrorResponse -> {
                ResultRequired.Error(resultRemote.throwable)
            }
        }
    }
}