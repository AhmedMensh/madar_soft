package com.example.madarsofttask.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "name") val name: String ? = null,
    @ColumnInfo(name = "age") val age: Int? = null,
    @ColumnInfo(name = "job_title") val jobTitle: String? = null,
    @ColumnInfo(name = "gender") val gender: String ? = null,
)