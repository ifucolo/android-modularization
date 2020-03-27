package com.example.data.remote.source

import com.example.data.extensions.mapRemoteErrors
import com.example.data.remote.api.ServerApi
import com.example.data.remote.mapper.AndroidJobMapper
import com.example.data.remote.model.JobsPayload
import com.example.domain.entities.AndroidJob
import com.example.domain.responses.ResultRemote
import io.reactivex.Single
import java.lang.Exception

interface RemoteDataSource {
    suspend fun getJobs(): ResultRemote<JobsPayload>
}

class RemoteDataSourceImpl(
    private val serverApi: ServerApi
): RemoteDataSource {

    override suspend fun getJobs(): ResultRemote<JobsPayload> {
        return try {
            val jobsPayload = serverApi.fetchJobs()

            ResultRemote.Success(
                response = jobsPayload
            )
        } catch (throwable: Throwable) {
            throwable.mapRemoteErrors()
        }
    }
}