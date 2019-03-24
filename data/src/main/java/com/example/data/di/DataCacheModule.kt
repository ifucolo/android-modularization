package com.example.data.di

import com.example.data.local.database.JobsDataBase
import com.example.data.local.source.JobsCacheDataSource
import com.example.data.local.source.JobsCacheSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val cacheDataModule = module {
    single { JobsDataBase.createDataBase(androidContext()) }
    factory<JobsCacheDataSource> { JobsCacheSourceImpl(jobsDao = get()) }
}