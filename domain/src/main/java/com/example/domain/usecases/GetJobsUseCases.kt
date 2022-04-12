package com.example.domain.usecases

import com.example.domain.entities.AndroidJob
import com.example.domain.repository.AndroidJobsRepository
import com.example.domain.responses.ResultRequired
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


interface GetJobsUseCases{
    suspend fun fetchJobs()
    fun addJob()

    val stream: MutableStateFlow<ResultJobs>

    sealed class ResultJobs {
        data class Jobs(val list: List<AndroidJob>): ResultJobs()
        object NoJobs: ResultJobs()
        object Error: ResultJobs()
        object Loading: ResultJobs()
    }
}

class GetJobsUseCasesImpl @Inject  constructor(
    private val repository: AndroidJobsRepository
): GetJobsUseCases {

    override val stream: MutableStateFlow<GetJobsUseCases.ResultJobs> = MutableStateFlow(GetJobsUseCases.ResultJobs.Loading)

    override suspend fun fetchJobs() {
        when(val result = repository.getJobs()) {
            is ResultRequired.Success -> {
                when {
                    result.result.isEmpty() -> {
                        stream.emit(GetJobsUseCases.ResultJobs.NoJobs)
                    }
                    else -> {
                        stream.emit(GetJobsUseCases.ResultJobs.Jobs(result.result.reversed()))
                    }
                }
            }
            is ResultRequired.Error -> {
                println(result.throwable.message)
                stream.emit(GetJobsUseCases.ResultJobs.Error)
            }
            ResultRequired.Loading -> {
                stream.emit(GetJobsUseCases.ResultJobs.Loading)
            }
        }
    }

    override fun addJob() {
        repository.add()
    }
}

