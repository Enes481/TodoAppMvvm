package com.enestigli.todoapp.di

import android.content.Context
import androidx.room.Room
import com.enestigli.todoapp.domain.repository.INoteRepository
import com.enestigli.todoapp.data.repository.NoteRepository
import com.enestigli.todoapp.room.NoteDao
import com.enestigli.todoapp.room.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object AppModule {

    @Singleton
    @Provides
    fun injectRoomDatabase(
        @ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        NoteDatabase::class.java,
        "note.db").build()


    @Singleton
    @Provides
    fun injectDao(database:NoteDatabase) = database.noteDao()

    @Singleton
    @Provides
    fun injectRepository(dao:NoteDao) = NoteRepository(dao) as INoteRepository



}