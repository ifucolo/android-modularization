package com.example.data.di

import com.example.data.AndroidJobsRepositoryImpl
import com.example.domain.repository.AndroidJobsRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<AndroidJobsRepository> {
        AndroidJobsRepositoryImpl(
            jobsCacheDataSource = get(),
            remoteDataSource = get()
        )
    }
}

val dataModules = listOf(remoteDataSourceModule, repositoryModule, cacheDataModule)