package com.example.mymoduleexample.di

import com.example.mymoduleexample.feature.list.AndroidJobListViewModel
import com.example.mymoduleexample.feature.list.AndroidJobsAdapter
import com.example.mymoduleexample.feature.main.MainViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { MainViewModel() }

    viewModel {
        AndroidJobListViewModel(
            jobsUseCase = get()
        )
    }
}