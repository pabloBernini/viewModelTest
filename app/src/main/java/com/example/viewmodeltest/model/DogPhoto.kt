package com.example.viewmodeltest.model

import kotlinx.serialization.Serializable

@Serializable
data class DogPhoto(
    val message: String,
    val status: String
)