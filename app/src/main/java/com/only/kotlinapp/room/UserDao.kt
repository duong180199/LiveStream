package com.only.kotlinapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getData(): List<User>

    @Query("SELECT * FROM message")
    fun getMessage(): List<Message>

    //truyền không giới hạn tham số đầu vào
    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)
}