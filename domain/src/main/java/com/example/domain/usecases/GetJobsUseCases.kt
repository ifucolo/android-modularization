package com.example.domain.usecases

import com.example.domain.entities.AndroidJob
import com.example.domain.repository.AndroidJobsRepository
import com.example.domain.responses.ResultRequired
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


interface GetJobsUseCases{
    fun getJobs(): Flow<ResultJobs>
    fun addJob()

    sealed class ResultJobs {
        data class Jobs(val list: List<AndroidJob>): ResultJobs()
        object NoJobs: ResultJobs()
        object Error: ResultJobs()
    }
}

class GetJobsUseCasesImpl(
    private val repository: AndroidJobsRepository
): GetJobsUseCases {

    override fun getJobs(): Flow<GetJobsUseCases.ResultJobs> {
        return repository.getJobs()
            .map {
                when(it) {
                    is ResultRequired.Success -> {
                        when {
                            it.result.isEmpty() -> GetJobsUseCases.ResultJobs.NoJobs
                            else -> GetJobsUseCases.ResultJobs.Jobs( it.result.reversed())
                        }
                    }
                    is ResultRequired.Error -> {
                        println(it.throwable.message)
                        GetJobsUseCases.ResultJobs.Error
                    }
                }
            }
    }

    override fun addJob() {
        repository.add()
    }
}

