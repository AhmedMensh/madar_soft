package com.example.madarsofttask.data.di

import android.content.Context
import androidx.room.Room
import com.example.madarsofttask.data.local.UsersDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun providesUserDao(usersDatabase: UsersDatabase) = usersDatabase.userDao()

    @Provides
    @Singleton
    fun providesUserDatabase(@ApplicationContext context: Context): UsersDatabase =
        Room.databaseBuilder(context, UsersDatabase::class.java, "UserDatabase").build()
}