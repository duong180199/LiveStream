package com.only.kotlinapp.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

@androidx.room.Database(entities = [User::class], version = 1)
abstract class Database() : RoomDatabase() {
    companion object {
        private var instance: Database? = null
        fun getInstance(context: Context): Database? {
             if(instance == null) {
                synchronized(Database::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        Database::class.java,
                        "user.db"
                    ).build()
                }
            }
            return instance
        }
    }

    abstract fun userDao(): UserDao
}