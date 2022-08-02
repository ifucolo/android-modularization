package com.example.mymoduleexample.di

import com.example.data.AndroidJobsRepositoryImpl
import com.example.domain.repository.AndroidJobsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface ProductModule {
    @Binds
    fun provideAndroidJobsRepository(impl: AndroidJobsRepositoryImpl): AndroidJobsRepository
}