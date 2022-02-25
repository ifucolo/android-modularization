package com.example.data.local.source

import com.example.data.local.database.JobsDao
import com.example.data.local.model.AndroidJobCache
import kotlinx.coroutines.flow.Flow

interface JobsCacheDataSource {
    fun getJobs(): Flow<List<AndroidJobCache>>

    fun insertData(jobCache : AndroidJobCache)
    fun updateData(cacheList: List<AndroidJobCache>)
}

class JobsCacheSourceImpl(private val jobsDao: JobsDao): JobsCacheDataSource {

    override fun getJobs(): Flow<List<AndroidJobCache>> = jobsDao.getJobs()

    override fun insertData(jobCache: AndroidJobCache) {
        jobsDao.insert(jobCache)
    }

    override fun updateData(cacheList: List<AndroidJobCache>) {
        jobsDao.updateData(cacheList)
    }
}