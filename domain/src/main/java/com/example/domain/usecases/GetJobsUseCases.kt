package com.example.domain.usecases

import com.example.domain.entities.AndroidJob
import com.example.domain.repository.AndroidJobsRepository
import io.reactivex.Observable
import io.reactivex.Scheduler

class GetJobsUseCases(
    private val repository: AndroidJobsRepository,
    private val scheduler: Scheduler
) {

    fun execute(): Observable<List<AndroidJob>> {
        return repository.getJobs()
            .subscribeOn(scheduler)
    }
}