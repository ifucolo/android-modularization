package com.example.data

import com.example.data.local.mapper.AndroidJobCacheMapper
import com.example.data.local.source.JobsCacheDataSource
import com.example.data.remote.mapper.AndroidJobMapper
import com.example.data.remote.source.RemoteDataSource
import com.example.domain.entities.AndroidJob
import com.example.domain.repository.AndroidJobsRepository
import com.example.domain.responses.ResultRemote
import com.example.domain.responses.ResultRequired
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import javax.inject.Inject

class AndroidJobsRepositoryImpl @Inject constructor(
    private val jobsCacheDataSource: JobsCacheDataSource,
    private val remoteDataSource: RemoteDataSource
    ): AndroidJobsRepository {

    override suspend fun getJobs(): ResultRequired<List<AndroidJob>> =
        withContext(Dispatchers.IO) {
            getJobsRemote()

        //            jobsCacheDataSource.getJobs()
//                .map { cacheList ->
//                    val result = when {
//                        cacheList.isEmpty() -> getJobsRemote()
//                        else -> {
//                            val jobs = AndroidJobCacheMapper.map(cacheList)
//                            ResultRequired.Success(jobs)
//                        }
//                    }
//
//                    result
//                }
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

        return when(val resultRemote = remoteDataSource.getJobs()) {
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