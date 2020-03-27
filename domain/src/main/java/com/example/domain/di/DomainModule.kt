package com.example.domain.di

import com.example.domain.usecases.GetJobsUseCases
import com.example.domain.usecases.GetJobsUseCasesImpl
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module

val useCaseModule = module {

    factory<GetJobsUseCases> {
        GetJobsUseCasesImpl(
            repository = get()
        )
    }
}

val domainModule = listOf(useCaseModule)