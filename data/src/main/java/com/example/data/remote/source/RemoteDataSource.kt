package com.example.data.remote.source

import com.example.domain.entities.AndroidJob
import io.reactivex.Single

interface RemoteDataSource {
    fun getJobs(): Single<List<AndroidJob>>
}