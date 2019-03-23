package com.example.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.model.AndroidJobCache
import io.reactivex.Single

@Dao
interface JobsDao {

    @Query("SELECT * FROM jobs")
    fun getJobs(): Single<List<AndroidJobCache>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJob(vararg androidJob: AndroidJobCache)
}