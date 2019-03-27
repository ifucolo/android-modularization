package com.example.data.remote.mapper

import com.example.data.remote.model.AndroidJobPayload
import com.example.data.remote.model.JobsPayload
import com.example.domain.entities.AndroidJob

object AndroidJobMapper {

    fun map(payload: JobsPayload) = payload.jobsPayload.map { map(it) }

    private fun map(payload: AndroidJobPayload) = AndroidJob(
        title = payload.title,
        experienceTimeRequired = payload.requiredExperienceYears.toString(),
        native = payload.native,
        country = payload.country
    )
}