package com.example.viewmodeltest.ui.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    var searchInput = mutableStateOf("")
    private var filteredDogs = mutableListOf<Dog>()
    var breed = mutableStateOf("")

    private var nextId = 0

    private val dogs = mutableListOf(
        Dog(nextId++, "Buddy", "Labrador"),
        Dog(nextId++, "aa", "dd"),
        Dog(nextId++, "sada", "asdsda"),
        Dog(nextId++, "AADSA", "asd"),
        Dog(nextId++, "zzzzz", "zzzz"),

    )
    init {
        filteredDogs.addAll(dogs)
        updateList()
    }



    fun dogCount(): Int {
        return dogs.size
    }
    fun favCount(): Int {
        return dogs.count { it.isFavorite }
    }
    private fun updateList() {
        val sortedDogs = dogs.sortedWith(
            compareByDescending<Dog> { it.isFavorite }
                .thenBy { it.name }
        )
        _uiState.update {
            UiState.Success(sortedDogs.toList())
        }
    }
    fun addDog(name: String, breed: String) {
        dogs.add(Dog(nextId++, name, breed))
        updateList()
    }

    fun removeDog(dogName: String) {
        dogs.removeIf { it.name == dogName }
        updateList()
    }

    fun triggerFav(name: String) {
        val element = dogs.find { it.name == name } ?: return
        val index = dogs.indexOf(element)
        if (index != -1) {
            val updatedDog = element.copy(isFavorite = !element.isFavorite)
            dogs[index] = updatedDog
            updateList()
        }
    }


    fun filterList() {
        val trimmedQuery = searchInput.value.trim().lowercase()
        filteredDogs.clear()
        if (trimmedQuery.isBlank()) {
            filteredDogs.addAll(dogs)
        }
        else {
            filteredDogs.addAll(dogs.filter {
                it.name.lowercase().contains(trimmedQuery) ||
                        it.breed.lowercase().contains(trimmedQuery)
            })
        }
        val sortedDogs = filteredDogs.sortedWith(
            compareByDescending<Dog> { it.isFavorite }
                .thenBy { it.name }
        )
        _uiState.update {
            UiState.Success(sortedDogs.toList())
        }
    }

    sealed class UiState {
        data object Loading : UiState()
        data class Success(val data: List<Dog>) : UiState()
    }
}