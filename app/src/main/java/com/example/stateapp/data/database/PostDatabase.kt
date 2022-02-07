package com.example.stateapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.stateapp.data.database.dao.PostDao
import com.example.stateapp.model.Post

@Database(entities = [Post::class], version = 1)
abstract class PostDatabase : RoomDatabase() {
    abstract fun getPostDao(): PostDao
}