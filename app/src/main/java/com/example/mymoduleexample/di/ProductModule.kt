package com.example.mymoduleexample.di

import com.example.data.AndroidJobsRepositoryImpl
import com.example.data.local.source.JobsCacheDataSource
import com.example.data.local.source.JobsCacheSourceImpl
import com.example.data.remote.source.RemoteDataSource
import com.example.data.remote.source.RemoteDataSourceImpl
import com.example.domain.repository.AndroidJobsRepository
import com.example.domain.usecases.GetJobsUseCases
import com.example.domain.usecases.GetJobsUseCasesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface ProductModule {

    @Binds
    fun provideGetJobsUseCases(impl: GetJobsUseCasesImpl): GetJobsUseCases

    @Binds
    fun provideAndroidJobsRepository(impl: AndroidJobsRepositoryImpl): AndroidJobsRepository

    @Binds
    fun provideRemoteDataSource(impl: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    fun provideJobsCacheDataSource(impl: JobsCacheSourceImpl): JobsCacheDataSource
}