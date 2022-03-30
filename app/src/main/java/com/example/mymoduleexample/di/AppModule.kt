package com.example.mymoduleexample.di

import android.content.Context
import com.example.data.R
import com.example.data.di.providesOkHttpClient
import com.example.data.local.database.JobsDao
import com.example.data.local.database.JobsDataBase
import com.example.data.remote.api.ServerApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun app(@ApplicationContext context: Context) = context

    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        providesOkHttpClient()

    @Provides
    fun provideWebService(
        okHttpClient: OkHttpClient,
        @ApplicationContext context: Context
    ): ServerApi = com.example.data.di.createWebService<ServerApi>(
        okHttpClient = okHttpClient,
        url = context.getString(R.string.base_url)
    )

    @Provides
    fun provideWJobsDao(@ApplicationContext context: Context): JobsDao =
        JobsDataBase.createDataBase(context)
}