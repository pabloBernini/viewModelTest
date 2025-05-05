package com.example.viewmodeltest.ui.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.viewmodeltest.model.Dog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DogsListVM : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    var name = mutableStateOf("")
    var breed = mutableStateOf("")

    private var nextId = 0

    private val dogs = mutableListOf(
        Dog(nextId++, "Buddy", "Labrador"),

    )
    init {
        _uiState.update {
            UiState.Success(
                dogs.toList()
            )
        }
    }



    fun addDog(name: String, breed: String) {
        dogs.add(Dog(nextId++, name, breed))
        _uiState.update {
            UiState.Success(dogs.toList())
        }
    }

    fun removeDog(dogName: String) {
        dogs.removeIf { it.name == dogName }
        _uiState.update {
            UiState.Success(dogs.toList())
        }
    }

    fun triggerFav(id: Int) {
        val dogIndex = dogs.indexOfFirst { it.id == id }
        if (dogIndex >= 0) {
            dogs[dogIndex].isFavorite = !dogs[dogIndex].isFavorite
        }
        _uiState.update {
            UiState.Success(dogs.toList())
        }
    }

    sealed class UiState {
        data object Loading : UiState()
        data class Success(val data: List<Dog>) : UiState()
    }
}