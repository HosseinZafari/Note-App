package com.hosseinzafari.github.framework.todoapp.feature_note.data.data_source.db.dao

import androidx.room.*
import com.hosseinzafari.github.framework.todoapp.feature_note.data.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

/*
    @created in 07/12/2021 - 4:12 PM
    @project Todo App
    @author Hossein Zafari
    @email  hosseinzafari2000@gmail.com
*/

@Dao
interface NoteDao {

    @Query("SELECT * FROM note_tb")
    fun getNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM note_tb WHERE uid=:id")
    suspend fun getNoteById(id: Int): NoteEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(noteModel: NoteEntity)

    @Delete
    suspend fun deleteNote(noteModel: NoteEntity)
}