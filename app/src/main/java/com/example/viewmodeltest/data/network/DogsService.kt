package com.example.viewmodeltest.data.network

import com.example.viewmodeltest.model.DogPhoto
import retrofit2.http.GET

interface DogsService {

    @GET("image/random")
    suspend fun getRandomDogImage(): DogPhoto
}