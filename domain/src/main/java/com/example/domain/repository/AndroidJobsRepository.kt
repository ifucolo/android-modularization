package com.example.domain.repository

import com.example.domain.entities.AndroidJob
import io.reactivex.Single

interface AndroidJobsRepository {
    fun getJobs(forceUpdate: Boolean): Single<List<AndroidJob>>
}