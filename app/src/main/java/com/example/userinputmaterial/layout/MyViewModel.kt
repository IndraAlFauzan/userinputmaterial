package com.example.userinputmaterial.layout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userinputmaterial.data.UserModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {

    // Internally, we use a StateFlow, because we want to update the data
    private val _uiState = MutableStateFlow(UserModel())
    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<UserModel> = _uiState

    fun getData(username: String,  email: String, jk: String){

        // Create a new UserModel instance
        val newUserModel = UserModel(username, email, jk)
        // Launch a coroutine that reads from a remote data source and updates _uiState
        viewModelScope.launch {
            _uiState.value = newUserModel
        }
    }

}