package com.example.mymoduleexample.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymoduleexample.feature.viewmodel.BaseViewModel
import com.example.mymoduleexample.utils.Event

class MainViewModel: BaseViewModel() {

    private val _mainActionLiveData = MutableLiveData<Event<MainAction>>()
    val mainActionLiveData: LiveData<Event<MainAction>> = _mainActionLiveData

    fun onShowAndroidJobsRequire() {
        _mainActionLiveData.postValue(Event(MainAction.SHOW_JOBS))
    }

    fun onOutAppLiveData() {
        _mainActionLiveData.postValue(Event(MainAction.LEAVE_APP))
    }
}

sealed class MainAction {
    object SHOW_JOBS: MainAction()
    object LEAVE_APP: MainAction()
}