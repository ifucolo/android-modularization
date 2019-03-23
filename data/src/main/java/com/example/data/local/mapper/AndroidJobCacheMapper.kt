package com.example.data.local.mapper

import com.example.data.local.model.AndroidJobCache
import com.example.domain.entities.AndroidJob

object AndroidJobCacheMapper {

    fun map(cacheData: List<AndroidJobCache>) = cacheData.map { map(it) }

    fun map(cacheData: AndroidJobCache) = AndroidJob(
        title = cacheData.title,
        experienceTimeRequired = cacheData.requiredExperienceYears,
        native = cacheData.native,
        country = cacheData.country
    )

    fun map(data: AndroidJob) = AndroidJobCache(
        title = data.title,
        requiredExperienceYears = data.experienceTimeRequired,
        native = data.native,
        country = data.country
    )
}