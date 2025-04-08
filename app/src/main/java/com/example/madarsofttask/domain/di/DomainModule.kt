package com.example.madarsofttask.domain.di

import com.example.madarsofttask.data.repositories.UserRepositoryImpl
import com.example.madarsofttask.domain.repositories.IUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Singleton
    @Binds
    abstract fun bindUsersRepositoryImpl(
        userRepositoryImpl: UserRepositoryImpl
    ): IUserRepository



}