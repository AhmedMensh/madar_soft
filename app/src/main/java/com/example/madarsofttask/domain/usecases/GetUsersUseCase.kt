package com.example.madarsofttask.domain.usecases

import com.example.madarsofttask.data.local.entities.UserEntity
import com.example.madarsofttask.domain.models.UserModel
import com.example.madarsofttask.domain.repositories.IUserRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val iUserRepository: IUserRepository
) {

    suspend fun invoke(): List<UserModel> {

        return iUserRepository.getUsers().map {
            UserModel(
                id = it.id,
                name = it.name.orEmpty(),
                age = it.age.toString() ,
                gender = it.gender.orEmpty(),
                jobTitle = it.jobTitle.orEmpty(),
            )
        }
    }
}