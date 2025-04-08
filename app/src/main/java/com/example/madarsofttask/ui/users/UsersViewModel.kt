package com.example.madarsofttask.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madarsofttask.domain.models.UserModel
import com.example.madarsofttask.domain.usecases.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UsersUIState>()
    val uiState: LiveData<UsersUIState> get() = _uiState

    init {
        getUsers()
    }

    private fun getUsers() {
        _uiState.value = UsersUIState.Loading(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val users = getUsersUseCase.invoke()
                withContext(Dispatchers.Main) {
                    _uiState.value = UsersUIState.Loading(false)
                    _uiState.value = UsersUIState.Success(users)

                }
            } catch (e: Exception) {
                _uiState.value = UsersUIState.Loading(false)
                _uiState.value = UsersUIState.Error(e.message.orEmpty())

            }
        }
    }
}

sealed class UsersUIState {
    data class Loading(val isLoading: Boolean) : UsersUIState()
    data class Success(val users: List<UserModel>) : UsersUIState()
    data class Error(val error: String) : UsersUIState()

}

