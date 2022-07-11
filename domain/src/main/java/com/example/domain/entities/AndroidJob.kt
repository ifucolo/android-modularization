package com.example.domain.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
class Jobs(
    @Json(name ="jobs") val jobs: List<AndroidJob>
)

@JsonClass(generateAdapter = true)
data class AndroidJob(
    @Json(name = "title") val title: String,
    @Json(name = "required_experience_years") val experienceTimeRequired: String,
    @Json(name = "native") val native: Boolean,
    @Json(name = "country") val country: String
)
