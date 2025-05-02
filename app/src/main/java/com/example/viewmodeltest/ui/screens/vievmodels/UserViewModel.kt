package com.example.viewmodeltest.ui.screens.vievmodels
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    val userName = mutableStateOf("Gość")
    val userAge = mutableStateOf(0)

    fun updateUserName(name: String) {
        userName.value = name
    }

    fun updateUserAge(age: Int) {
        userAge.value = age
    }
}