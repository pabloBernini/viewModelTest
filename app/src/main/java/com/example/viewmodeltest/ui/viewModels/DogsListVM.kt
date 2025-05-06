package com.example.viewmodeltest.ui.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.viewmodeltest.model.Dog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import com.example.viewmodeltest.data.DogsRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted
import com.example.viewmodeltest.DogsListApplication
import kotlinx.coroutines.launch
import kotlin.collections.remove


class DogsListVM(
    private val dogsRepository: DogsRepository
) : ViewModel() {


    sealed interface UiState {
        object Loading: UiState
        data class Error(val throwable: Throwable): UiState
        data class Success(val data: List<Dog>): UiState
    }

    var name = mutableStateOf("")

///// name powinno byc puste
val uiState: StateFlow<UiState> = dogsRepository
    .dogs
    .map<List<Dog>, UiState> { UiState.Success(data = it) }
    .catch { emit(UiState.Error(it)) }
    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UiState.Loading)
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)






    private var nextId = 0

    private val dogs = mutableListOf(
        Dog(nextId++, "Buddy", "Labrador"),
        Dog(nextId++, "aa", "dd"),
        Dog(nextId++, "sada", "asdsda"),
        Dog(nextId++, "AADSA", "asd"),
        Dog(nextId++, "zzzzz", "zzzz"),

    )
    init {
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
        viewModelScope.launch {
            dogsRepository.add(name, breed)
        }
        /////this.name.value = ""
    }

    fun removeDog(id: Int) {
        viewModelScope.launch {
            dogsRepository.remove(id)
        }
    }
    fun removeDogByName(name: String) {
        viewModelScope.launch {
            dogsRepository.removeByName(name)
        }
    }

    fun triggerFav(id: Int) {
        viewModelScope.launch {
            dogsRepository.triggerFav(id)
        }
    }


    fun filterList(name: String) {
        viewModelScope.launch {
            dogsRepository.getDogsByName(name).collect { dogs ->
                _uiState.value = UiState.Success(dogs)
            }
        }
    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as DogsListApplication)
                val dogsRepository = application.container.dogsRepository
                DogsListVM(dogsRepository)
            }
        }
    }

}