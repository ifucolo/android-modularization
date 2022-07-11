package com.example.data.local.mapper

import com.example.data.local.model.AndroidJobCache
import com.example.domain.entities.AndroidJob


fun AndroidJobCache.asExternalModel() = AndroidJob(
    title = title,
    experienceTimeRequired = requiredExperienceYears.toString(),
    native = native,
    country = country
)

fun AndroidJob.asCache() = AndroidJobCache(
    title = title,
    requiredExperienceYears = experienceTimeRequired.toInt(),
    native = native,
    country = country
)

