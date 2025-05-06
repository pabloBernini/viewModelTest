package com.example.viewmodeltest.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DogEntity::class], version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun dogDao(): DogEntityDao
}