package com.example.madarsofttask.domain.usecases

import com.example.madarsofttask.domain.models.Errors
import com.example.madarsofttask.domain.models.UserModel
import javax.inject.Inject

class ValidateUserInputUseCase @Inject constructor() {


    fun invoke(userInput: UserModel): Errors {
        if (userInput.name.isEmpty()) {
            return Errors.EMPTY_INPUT
        }
        if (userInput.age.toString().isEmpty()) {
            return Errors.EMPTY_INPUT
        }

        if (userInput.jobTitle.isEmpty()) {
            return Errors.EMPTY_INPUT
        }

        if (userInput.gender.isEmpty()) {
            return Errors.EMPTY_INPUT
        }
//        if (userInput.age.toInt() !in 1..150) {
//            return Errors.INVALID_AGE
//        }
        return Errors.NONE
    }
}