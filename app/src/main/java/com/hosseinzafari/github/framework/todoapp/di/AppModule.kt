package com.hosseinzafari.github.framework.todoapp.di

import android.app.Application
import androidx.room.Room
import com.hosseinzafari.github.framework.todoapp.NoteApplication
import com.hosseinzafari.github.framework.todoapp.feature_note.data.data_source.db.NoteDatabase
import com.hosseinzafari.github.framework.todoapp.feature_note.data.entity.NoteEntity
import com.hosseinzafari.github.framework.todoapp.feature_note.data.entity.NoteMapper
import com.hosseinzafari.github.framework.todoapp.feature_note.data.repository.NoteRepositoryImpl
import com.hosseinzafari.github.framework.todoapp.feature_note.domain.model.NoteModel
import com.hosseinzafari.github.framework.todoapp.feature_note.domain.repository.NoteRepository
import com.hosseinzafari.github.framework.todoapp.feature_note.domain.use_case.*
import com.hosseinzafari.github.framework.todoapp.feature_note.domain.utils.DomainMapperList
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*

@created in 07/12/2021 - 7:36 PM
@project Todo App
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDataBase(applicationContext: Application) : NoteDatabase {
        return Room.databaseBuilder(
            applicationContext ,
            NoteDatabase::class.java ,
            NoteApplication.DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteMapper(): DomainMapperList<NoteEntity , NoteModel> {
        return NoteMapper()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase , domainMapperList: DomainMapperList<NoteEntity , NoteModel>): NoteRepository {
        return NoteRepositoryImpl(db.noteDao , domainMapperList)
    }


    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository),
            getNote = GetNote(repository)
        )
    }
}