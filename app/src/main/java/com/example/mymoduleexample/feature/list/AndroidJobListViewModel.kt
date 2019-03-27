package com.example.mymoduleexample.feature.list

import androidx.lifecycle.MutableLiveData
import com.example.domain.entities.AndroidJob
import com.example.domain.usecases.GetJobsUseCases
import com.example.mymoduleexample.feature.viewmodel.BaseViewModel
import com.example.mymoduleexample.feature.viewmodel.StateMachineSingle
import com.example.mymoduleexample.feature.viewmodel.ViewState
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.plusAssign

class AndroidJobListViewModel(val useCase: GetJobsUseCases, val uiScheduler: Scheduler): BaseViewModel() {

    val state = MutableLiveData<ViewState<List<AndroidJob>>>().apply {
        value = ViewState.Loading
    }

    fun getJobs(forceUpdate: Boolean = false) {
        disposables += useCase.execute(forceUpdate = forceUpdate)
            .compose(StateMachineSingle())
            .observeOn(uiScheduler)
            .subscribe(
                {
                    state.postValue(it)
                },
                { }
            )
    }

    fun onTryAgainRequired() {
        getJobs(forceUpdate = true)
    }
}