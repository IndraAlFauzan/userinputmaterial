package com.example.userinputmaterial.layout

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userinputmaterial.data.UserModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {

    var setNama by mutableStateOf("")
    var setEmail by mutableStateOf("")
    var setJk by mutableStateOf("")
    var setStatus by mutableStateOf("")
    var setAlamat by mutableStateOf("")
    var setTelp by mutableStateOf("")

    // Internally, we use a StateFlow, because we want to update the data
    private val _uiState = MutableStateFlow(UserModel())
    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<UserModel> = _uiState.asStateFlow()

    fun getData(username: String,  email: String, sex: String, status: String, alamat: String, telp: String) {

        setNama = username
        setEmail = email
        setJk = sex
        setStatus = status
        setAlamat = alamat
        setTelp = telp
    }


    fun saveJenisKelamin( jk: String){
            // Launch a coroutine that reads from a remote data source and updates _uiState
            _uiState.update {  currentState -> currentState.copy(sex = jk) }

    }

    fun saveStatus( status: String){
            // Launch a coroutine that reads from a remote data source and updates _uiState
            _uiState.update {  currentState -> currentState.copy(status = status) }

    }



}