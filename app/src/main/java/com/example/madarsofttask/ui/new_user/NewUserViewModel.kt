package com.example.madarsofttask.ui.new_user

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madarsofttask.domain.models.Errors
import com.example.madarsofttask.domain.models.UserModel
import com.example.madarsofttask.domain.usecases.AddNewUserUseCase
import com.example.madarsofttask.domain.usecases.ValidateUserInputUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NewUserViewModel @Inject constructor(
    private val validateUserInputUseCase: ValidateUserInputUseCase,
    private val addNewUserUseCase: AddNewUserUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<NewUserUIState>()
    val uiState: LiveData<NewUserUIState> get() = _uiState

    var gender: String? = null

    fun addNewUser(userModel: UserModel) {
        val error = validateUserInputUseCase.invoke(userModel)
        if (error == Errors.NONE) {
            _uiState.value = NewUserUIState.Loading(true)

            viewModelScope.launch(Dispatchers.IO) {
                try {
                    addNewUserUseCase.invoke(userModel)
                    withContext(Dispatchers.Main){
                        _uiState.value = NewUserUIState.Loading(false)
                        _uiState.value = NewUserUIState.Success
                        _uiState.value = NewUserUIState.Ideal
                    }
                }catch (e: Exception){
                    _uiState.value = NewUserUIState.Error(Errors.GENERAL_ERROR)
                }

            }
        } else {
            _uiState.value = NewUserUIState.Error(error)
        }
    }

}

sealed class NewUserUIState {
    data object Ideal : NewUserUIState()
    data class Loading(val isLoading: Boolean) : NewUserUIState()
    data object Success : NewUserUIState()
    data class Error(val error: Errors) : NewUserUIState()

}
