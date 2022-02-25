package com.example.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.data.local.model.AndroidJobCache
import kotlinx.coroutines.flow.Flow

@Dao
interface JobsDao {

    @Query("SELECT * FROM jobs")
    fun getJobs(): Flow<List<AndroidJobCache>>

    @Transaction
    fun updateData(users: List<AndroidJobCache>) {
        deleteAll()
        insertAll(users)
    }

    @Insert
    fun insertAll(users: List<AndroidJobCache>)

    @Insert
    fun insert(vararg job: AndroidJobCache)

    @Query("DELETE FROM jobs")
    fun deleteAll()
}