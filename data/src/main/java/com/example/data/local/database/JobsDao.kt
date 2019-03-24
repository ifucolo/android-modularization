package com.example.data.local.database

import androidx.room.*
import com.example.data.local.model.AndroidJobCache
import io.reactivex.Single

@Dao
interface JobsDao {

    @Query("SELECT * FROM jobs")
    fun getJobs(): Single<List<AndroidJobCache>>

    @Transaction
    fun updateData(users: List<AndroidJobCache>) {
        deleteAllUsers()
        insertAll(users)
    }

    @Insert
    fun insertAll(users: List<AndroidJobCache>)

    @Query("DELETE FROM jobs")
    fun deleteAllUsers()
}