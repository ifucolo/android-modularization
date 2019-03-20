package com.example.domain.repository

import com.example.domain.entities.AndroidJob
import io.reactivex.Observable

interface AndroidJobsRepository {
    fun getJobs(): Observable<List<AndroidJob>>
}