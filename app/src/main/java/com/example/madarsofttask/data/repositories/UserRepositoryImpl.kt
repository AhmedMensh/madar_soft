package com.example.madarsofttask.data.repositories

import com.example.madarsofttask.data.local.LocalDataSource
import com.example.madarsofttask.data.local.entities.UserEntity
import com.example.madarsofttask.domain.repositories.IUserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
) : IUserRepository {
    override suspend fun addNewUser(user: UserEntity) : Long{
        return localDataSource.addNewUser(user)
    }

    override suspend fun getUsers(): List<UserEntity> {
        return localDataSource.getUsers()
    }
}