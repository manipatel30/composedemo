package com.jetpackcomposedemo.ui.room.di

import android.app.Application
import androidx.room.Room
import com.jetpackcomposedemo.ui.room.TodoDatabase
import com.jetpackcomposedemo.ui.room.model.ToDoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(application: Application): TodoDatabase {
        return Room.databaseBuilder(
            application,
            TodoDatabase::class.java,
            "TodoDdatabase.db"
        ).fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    @Singleton
    fun providesDao(database: TodoDatabase): ToDoDao = database.getDao()
}