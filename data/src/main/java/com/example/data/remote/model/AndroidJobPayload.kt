package com.example.data.remote.model

import com.example.domain.entities.AndroidJob
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonQualifier
import com.squareup.moshi.ToJson

data class JobsResults(
    val jobs: List<AndroidJob>
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

    @ToJson
    fun toJson(@WrapperJobsResults value:  List<AndroidJob>): JobsResults {
        throw UnsupportedOperationException()
    }
}