package com.example.data.local.source

import com.example.data.local.database.JobsDao
import com.example.data.local.mapper.AndroidJobCacheMapper
import com.example.domain.entities.AndroidJob
import io.reactivex.Single

class JobsCacheSourceImpl(private val jobsDao: JobsDao): JobsCacheDataSource {

    override fun getJobs(): Single<List<AndroidJob>> {
        return jobsDao.getJobs()
            .map { AndroidJobCacheMapper.map(it) }
    }

    override fun insertJob(androidJob: AndroidJob) {
        jobsDao.insertJob(AndroidJobCacheMapper.map(androidJob))
    }
}