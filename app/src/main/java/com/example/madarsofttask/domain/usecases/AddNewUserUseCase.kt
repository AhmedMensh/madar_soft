package com.example.madarsofttask.domain.usecases

import com.example.madarsofttask.data.local.entities.UserEntity
import com.example.madarsofttask.domain.models.UserModel
import com.example.madarsofttask.domain.repositories.IUserRepository
import javax.inject.Inject

class AddNewUserUseCase @Inject constructor(
    private val iUserRepository: IUserRepository
) {

    suspend fun invoke(user: UserModel) : Long{
        return iUserRepository.addNewUser(UserEntity(
            name = user.name,
            age = user.age.toInt(),
            jobTitle = user.jobTitle,
            gender = user.gender
        ))
    }
}