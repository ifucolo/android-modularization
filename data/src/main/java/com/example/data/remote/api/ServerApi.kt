package com.example.data.remote.api

import com.example.data.remote.model.WrapperJobsResults
import com.example.domain.entities.AndroidJob
import retrofit2.http.GET

interface ServerApi {

    @GET("/api/android-jobs")
    @WrapperJobsResults
    suspend fun fetchJobs(): List<AndroidJob>
}