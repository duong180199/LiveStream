package com.only.kotlinapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Message(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "message") val message : String?,
)