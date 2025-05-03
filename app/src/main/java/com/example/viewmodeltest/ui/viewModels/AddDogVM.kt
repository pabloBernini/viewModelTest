package com.example.viewmodeltest.ui.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

    class AddDogVM : ViewModel() {
        var name = mutableStateOf("")
        var breed = mutableStateOf("")

        fun updateName(newName: String) {
            name.value = newName
        }

        fun updateBreed(newBreed: String) {
            breed.value = newBreed
        }
        fun clear(){
            name.value = ""
            breed.value = ""
        }
}