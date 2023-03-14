package com.only.kotlinapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "name") val firstName: String?,
    @ColumnInfo(name = "comment") val comment: List<Message?>
)