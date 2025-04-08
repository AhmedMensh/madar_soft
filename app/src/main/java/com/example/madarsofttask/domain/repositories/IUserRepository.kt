package com.example.madarsofttask.domain.repositories

import com.example.madarsofttask.data.local.entities.UserEntity

interface IUserRepository {

    suspend fun addNewUser(user: UserEntity): Long
    suspend fun getUsers(): List<UserEntity>
}