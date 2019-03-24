package com.example.data.di

import com.example.data.remote.source.RemoteDataSource
import com.example.data.remote.source.RemoteDataSourceImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<RemoteDataSource> { RemoteDataSourceImpl(serverApi = get()) }
}

val dataModules = listOf(remoteDataSourceModule, repositoryModule, cacheDataModule)