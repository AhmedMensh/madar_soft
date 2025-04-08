package com.example.madarsofttask.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.madarsofttask.data.local.entities.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table")
    fun getUsers(): List<UserEntity>

    @Insert
    fun insertUser(user: UserEntity) : Long

}