package com.example.viewmodeltest.data

import com.example.viewmodeltest.data.database.DogEntity
import com.example.viewmodeltest.data.database.DogEntityDao
import com.example.viewmodeltest.data.network.DogsService
import com.example.viewmodeltest.model.Dog
import com.example.viewmodeltest.model.DogPhoto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface DogsRepository {
    val dogs: Flow<List<Dog>>

    suspend fun getRandomDogImage(): DogPhoto
    suspend fun add(name: String, breed: String)
    suspend fun remove(id: Int)
    suspend fun removeByName(name: String)
    suspend fun triggerFav(id: Int)
    suspend fun getDogsByName(name: String): Flow<List<Dog>>

}

class DefaultDogsRepository(
    private val dogsService: DogsService,
    private val dogDao: DogEntityDao
) : DogsRepository {

    override val dogs: Flow<List<Dog>> = dogDao.getSortedDogs().map { items ->
        items.map {
            Dog(
                id = it.uid,
                name = it.name,
                breed = it.breed,
                isFavorite = it.isFav
            )
        }
    }

    override suspend fun getRandomDogImage(): DogPhoto = dogsService.getRandomDogImage()

    override suspend fun add(name: String, breed: String) {
        dogDao.insertDog(DogEntity(name = name, breed = breed, isFav = false))
    }

    override suspend fun remove(id: Int) {
        dogDao.removeDog(id)
    }

    override suspend fun removeByName(name: String) {
        dogDao.removeDogByName(name)
    }

    override suspend fun triggerFav(id: Int) {
        dogDao.triggerFavDog(id)
    }

    override suspend fun getDogsByName(name: String): Flow<List<Dog>> {
        return dogs.map { dogList ->
            dogList.filter {
                it.name.contains(name, ignoreCase = true)
            }
        }
    }

}