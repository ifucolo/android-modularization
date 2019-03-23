package com.example.data.local.source

import com.example.domain.entities.AndroidJob
import io.reactivex.Single

interface JobsCacheDataSource {
    fun getJobs(): Single<List<AndroidJob>>
    fun insertJob(androidJob: AndroidJob)
}