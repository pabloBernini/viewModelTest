package com.example.viewmodeltest.ui.screens.vievmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    val count = mutableStateOf(0)

    fun incrementCount() {
        count.value++
    }
}