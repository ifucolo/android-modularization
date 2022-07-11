package com.example.data

import com.example.data.local.database.JobsDao
import com.example.data.local.mapper.asCache
import com.example.data.local.mapper.asExternalModel
import com.example.data.local.model.AndroidJobCache
import com.example.data.remote.api.ServerApi
import com.example.domain.entities.AndroidJob
import com.example.domain.repository.AndroidJobsRepository
import com.ifucolo.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AndroidJobsRepositoryImpl @Inject constructor(
    private val serverApi: ServerApi,
    private val jobsDao: JobsDao,
    @IoDispatcher
    private val coroutineDispatcher: CoroutineDispatcher
): AndroidJobsRepository {

    override fun getJobs(): Flow<List<AndroidJob>> {
        //return withContext(coroutineDispatcher) {
            return jobsDao.getJobs().map { jobs ->
                jobs.map(AndroidJobCache::asExternalModel)
            }.onEach {
                if (it.isEmpty()) {
                    fetchFreshJobs()
                }
            }
        //}
    }

    override fun add() {
        val androidJob = AndroidJob(
            title = "flow",
            experienceTimeRequired = (0..500).random().toString(),
            native = true,
            country = "Braziil"
        )

        val cacheJob = androidJob.asCache()
        jobsDao.insert(cacheJob)
    }

    override suspend fun fetchFreshJobs() {
        withContext(coroutineDispatcher) {
            serverApi.fetchJobs()
                .also { jobs ->
                    jobsDao.updateData(jobs.map { it.asCache() })
                }
        }
    }
}