package com.example.connectme.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val id: Int,
    val name: String,
    val phoneNumber: String,
    val profilePictureUrl: String
)