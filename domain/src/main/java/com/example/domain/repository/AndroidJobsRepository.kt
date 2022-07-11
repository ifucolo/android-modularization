package com.example.domain.repository

import com.example.domain.entities.AndroidJob
import com.example.domain.responses.ResultRequired
import kotlinx.coroutines.flow.Flow

interface AndroidJobsRepository {
    fun getJobs(): Flow<List<AndroidJob>>
    suspend fun fetchFreshJobs()

    fun add()
}
