package com.example.madarsofttask.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.madarsofttask.data.local.converters.DateConverter
import com.example.madarsofttask.data.local.entities.UserEntity


@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class UsersDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}

