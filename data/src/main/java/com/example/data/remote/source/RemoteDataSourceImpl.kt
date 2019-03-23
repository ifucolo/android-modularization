package com.example.data.remote.source

import com.example.data.remote.api.ServerApi
import com.example.data.remote.mapper.AndroidJobMapper
import com.example.domain.entities.AndroidJob
import io.reactivex.Single

class RemoteDataSourceImpl(private val serverApi: ServerApi):
    RemoteDataSource {

    override fun getJobs(): Single<List<AndroidJob>> {
        return serverApi.fetchJobs()
            .map { AndroidJobMapper.map(it) }
    }
}