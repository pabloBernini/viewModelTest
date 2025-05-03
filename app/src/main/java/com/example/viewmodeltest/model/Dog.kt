package com.example.viewmodeltest.model

data class Dog(
    val id: Int,
    val name: String,
    val breed: String,
    var isFavorite: Boolean = false
)