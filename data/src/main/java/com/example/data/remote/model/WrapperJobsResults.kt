package com.example.data.remote.model

import com.example.domain.entities.AndroidJob
import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.JsonQualifier

data class JobsResults(
    @Json(name = "jobs") val jobs: List<AndroidJob>
)

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class WrapperJobsResults

class AndroidJobPayloadConverter {
    @WrapperJobsResults
    @FromJson
    fun fromJson(json: JobsResults): List<AndroidJob> {
        return json.jobs
    }
}