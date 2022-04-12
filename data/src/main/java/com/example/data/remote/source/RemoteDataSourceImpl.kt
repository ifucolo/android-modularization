package com.example.data.remote.source

import com.example.data.extensions.mapRemoteErrors
import com.example.data.remote.api.ServerApi
import com.example.data.remote.model.JobsPayload
import com.example.domain.responses.ResultRemote
import javax.inject.Inject

interface RemoteDataSource {
    suspend fun getJobs(): ResultRemote<JobsPayload>
}

class RemoteDataSourceImpl @Inject constructor(
    private val serverApi: ServerApi
): RemoteDataSource {

    override suspend fun getJobs(): ResultRemote<JobsPayload> {
        return try {
            val jobsPayload = serverApi.fetchJobs()

            ResultRemote.Success(
                response = jobsPayload
            )
        } catch (throwable: Throwable) {
            println(throwable.message)
            throwable.mapRemoteErrors()
        }
    }
}