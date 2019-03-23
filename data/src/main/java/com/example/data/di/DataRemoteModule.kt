package com.example.data.di

import com.example.data.remote.api.ServerApi
import com.example.data.remote.source.RemoteDataSource
import com.example.data.remote.source.RemoteDataSourceImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val remoteDataSourceModule = module {
    factory { providesOkHttpClient(get()) }
    single { providesGson() }
    single { createWebService<ServerApi>(okHttpClient = get(), gson = get(), url =  ServerApi.API_URL) }

    factory<RemoteDataSource> { RemoteDataSourceImpl(serverApi = get()) }
}

fun providesGson(): Gson {
    val gsonBuilder = GsonBuilder()
    return gsonBuilder.create()
}

fun providesOkHttpClient(interceptorAuth: Interceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(interceptorAuth)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, gson: Gson, url: String): T {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(url)
        .client(okHttpClient)
        .build()
        .create(T::class.java)
}
