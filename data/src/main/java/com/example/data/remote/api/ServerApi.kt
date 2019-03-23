package com.example.data.remote.api

import com.example.data.remote.model.JobsPayload
import io.reactivex.Single
import retrofit2.http.GET

interface ServerApi {

    @GET("/android-jobs")
    fun fetchJobs(): Single<JobsPayload>

    companion object {
        const val API_URL = "https://demo8470178.mockable.io"
    }
}