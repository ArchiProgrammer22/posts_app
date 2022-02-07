package com.example.stateapp.di.modules

import android.content.Context
import androidx.room.Room
import com.example.stateapp.data.database.PostDatabase
import com.example.stateapp.data.database.dao.PostDao
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule(
    private val context: Context,
) {

    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideDao(context: Context): PostDao {
        return Room.databaseBuilder(
            context,
            PostDatabase::class.java,
            "posts"
        ).build().getPostDao()
    }
}