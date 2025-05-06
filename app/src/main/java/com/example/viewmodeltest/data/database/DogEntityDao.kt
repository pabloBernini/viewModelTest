package com.example.viewmodeltest.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DogEntityDao {

    @Query("SELECT * FROM dogs")
    fun getAllDogs(): Flow<List<DogEntity>>

    @Query("SELECT * FROM dogs ORDER BY name ASC")
    fun getSortedDogs(): Flow<List<DogEntity>>

    @Query("SELECT * FROM dogs WHERE isFav = 1")
    fun getAllFavDogs(): Flow<List<DogEntity>>

    @Query("UPDATE dogs SET isFav = CASE WHEN isFav = 1 THEN 0 ELSE 1 END WHERE uid = :id")
    suspend fun triggerFavDog(id: Int)

    @Insert
    suspend fun insertDog(dog: DogEntity)

    @Query("DELETE FROM dogs WHERE uid = :id")
    suspend fun removeDog(id: Int)

    @Query("DELETE FROM dogs WHERE name = :name")
    suspend fun removeDogByName(name: String)

}