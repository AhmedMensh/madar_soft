package com.example.madarsofttask.data.local

import com.example.madarsofttask.data.local.entities.UserEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val userDao: UserDao
) {

    fun getUsers(): List<UserEntity> {
        return userDao.getUsers()
    }

    fun addNewUser(user: UserEntity) : Long{
        return userDao.insertUser(user)
    }
}


