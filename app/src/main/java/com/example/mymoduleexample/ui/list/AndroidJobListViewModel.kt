package com.example.mymoduleexample.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.AndroidJob
import com.example.domain.usecases.GetJobsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AndroidJobListViewModel @Inject constructor(
    private val jobsUseCase: GetJobsUseCases
): ViewModel() {

    private val _state: MutableStateFlow<JobsUiStateState> = MutableStateFlow(JobsUiStateState.Loading)
    val state: StateFlow<JobsUiStateState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            jobsUseCase.stream.collect { result ->
                when(result) {
                    GetJobsUseCases.ResultJobs.Error -> _state.emit(JobsUiStateState.Error)
                    is GetJobsUseCases.ResultJobs.Jobs -> _state.emit(JobsUiStateState.Show(list = result.list))
                    GetJobsUseCases.ResultJobs.NoJobs -> _state.emit(JobsUiStateState.Empty)
                    GetJobsUseCases.ResultJobs.Loading -> _state.emit(JobsUiStateState.Loading)
                }
            }
        }
    }

    fun add() {
        viewModelScope.launch(Dispatchers.IO) {
            jobsUseCase.addJob()
        }
    }

    fun onTryAgainRequired() {
        viewModelScope.launch {
            jobsUseCase.fetchJobs()
        }
    }

    sealed class JobsUiStateState {
        data class Show(val list: List<AndroidJob>): JobsUiStateState()
        object Empty: JobsUiStateState()
        object Error: JobsUiStateState()
        object Loading: JobsUiStateState()
    }
}