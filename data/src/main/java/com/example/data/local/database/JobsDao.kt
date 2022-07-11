package com.example.data.local.database

import androidx.room.*
import com.example.data.local.model.AndroidJobCache
import kotlinx.coroutines.flow.Flow

@Dao
interface JobsDao {

    @Query("SELECT * FROM jobs")
    fun getJobs(): Flow<List<AndroidJobCache>>

    @Transaction
    fun updateData(jobs: List<AndroidJobCache>) {
        deleteAll()
        insertAll(jobs)
    }

    @Insert
    fun insertAll(jobs: List<AndroidJobCache>)

    @Insert
    fun insert(vararg job: AndroidJobCache)

    @Query("DELETE FROM jobs")
    fun deleteAll()
}