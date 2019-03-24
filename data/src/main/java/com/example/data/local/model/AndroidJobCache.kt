package com.example.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jobs")
data class AndroidJobCache(
    @PrimaryKey
    val title: String,
    val requiredExperienceYears: Int,
    val native: Boolean,
    val country: String
)
