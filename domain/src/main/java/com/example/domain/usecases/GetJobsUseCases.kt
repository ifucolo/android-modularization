package com.example.domain.usecases

import com.example.domain.entities.AndroidJob
import com.example.domain.repository.AndroidJobsRepository
import com.example.domain.responses.ResultRequired
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


interface GetJobsUseCases{
    fun fetchJobs()
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

    override fun fetchJobs() {
        repository.getJobs()
            .map {
                when(it) {
                    is ResultRequired.Success -> {
                        when {
                            it.result.isEmpty() -> {
                                stream.emit(GetJobsUseCases.ResultJobs.NoJobs)
                            }
                            else -> {
                                stream.emit(GetJobsUseCases.ResultJobs.Jobs( it.result.reversed()))
                            }
                        }
                    }
                    is ResultRequired.Error -> {
                        println(it.throwable.message)
                        stream.emit(GetJobsUseCases.ResultJobs.Error)
                    }
                    ResultRequired.Loading -> {
                        stream.emit(GetJobsUseCases.ResultJobs.Loading)
                    }
                }
            }
    }

    override fun addJob() {
        repository.add()
    }
}

