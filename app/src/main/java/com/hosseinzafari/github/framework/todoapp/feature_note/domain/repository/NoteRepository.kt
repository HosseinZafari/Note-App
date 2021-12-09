package com.hosseinzafari.github.framework.todoapp.feature_note.domain.repository

import com.hosseinzafari.github.framework.todoapp.feature_note.domain.model.NoteModel
import kotlinx.coroutines.flow.Flow

/*

@created in 07/12/2021 - 4:41 PM
@project Todo App
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

interface NoteRepository {

    fun getNotes(): Flow<List<NoteModel?>>

    suspend fun getNoteById(id: Int): NoteModel?

    suspend fun addNote(noteModel: NoteModel)

    suspend fun deleteNote(noteModel: NoteModel)

}