package com.example.mymoduleexample.ui.list

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.AndroidJob
import com.example.domain.repository.AndroidJobsRepository
import com.example.mymoduleexample.utils.Result
import com.example.mymoduleexample.utils.WhileUiSubscribed
import com.example.mymoduleexample.utils.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AndroidJobListViewModel @Inject constructor(
    private val repository: AndroidJobsRepository
): ViewModel() {

    private val jobs: Flow<Result<List<AndroidJob>>> =
        repository.getJobs().asResult()

    private val isRefreshing = MutableStateFlow(false)

    val uiState: StateFlow<JobsScreenUiState> = combine(
        jobs,
        isRefreshing
    ) { jobsResult, refreshing ->


        val jobsUiState = when (jobsResult) {
            is Result.Success -> JobsUiState.Success(jobsResult.data)
            is Result.Loading -> JobsUiState.Loading
            is Result.Error -> JobsUiState.Error
        }

        JobsScreenUiState(
            jobsUiState = jobsUiState,
            isRefreshing = refreshing
        )
    }
        .stateIn(
            scope = viewModelScope,
            started = WhileUiSubscribed,
            initialValue = JobsScreenUiState(
                jobsUiState = JobsUiState.Loading,
                isRefreshing = false
            )
        )

    fun add() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.add()
        }
    }

    fun onTryAgainRequired() {
        viewModelScope.launch {
            val refreshJobsDeferred = async { repository.fetchFreshJobs() }
            isRefreshing.emit(true)
            try {
                awaitAll(refreshJobsDeferred)
            } finally {
                isRefreshing.emit(false)
            }
        }
    }
}

data class JobsScreenUiState(
    val jobsUiState: JobsUiState,
    val isRefreshing: Boolean,
)

@Immutable
sealed interface JobsUiState {
    data class Success(val jobs: List<AndroidJob>) : JobsUiState
    object Error : JobsUiState
    object Loading : JobsUiState
}