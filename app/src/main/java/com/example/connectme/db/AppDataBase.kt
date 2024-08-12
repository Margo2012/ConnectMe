package com.example.connectme.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.connectme.data.model.User

@Database(entities = [User::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
}