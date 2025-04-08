package com.example.madarsofttask.domain.usecases

import com.example.madarsofttask.data.local.entities.UserEntity
import com.example.madarsofttask.domain.MainDispatcherRule
import com.example.madarsofttask.domain.models.UserModel
import com.example.madarsofttask.domain.repositories.IUserRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class GetUsersUseCaseTest{
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    @Test
    fun `test add new user`() = runTest{
        val repository = mockk<IUserRepository>()
        val useCase = GetUsersUseCase(repository)

        coEvery { repository.getUsers() } coAnswers { listOf(UserEntity(1,"Ahmed", 20, "Android Developer", "Male")) }

        val result = useCase.invoke()
        assertEquals(1, result)


    }

    @Test(expected = IllegalArgumentException::class)
    fun `test add new user then throw exception`() = runTest{
        val repository = mockk<IUserRepository>()
        val useCase = GetUsersUseCase(repository)

        coEvery { repository.getUsers() } coAnswers { throw IllegalArgumentException() }

        useCase.invoke()


    }
}