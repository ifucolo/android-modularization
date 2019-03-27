package com.example.mymoduleexample.feature.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    val showAndroidJobsLiveData = MutableLiveData<Boolean>()
    val outAppLiveData = MutableLiveData<Boolean>()

    fun onShowAndroidJobsRequire() {
        showAndroidJobsLiveData.postValue(true)
    }

    fun onOutAppLiveData() {
        outAppLiveData.postValue(true)
    }
}