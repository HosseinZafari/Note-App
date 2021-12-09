package com.hosseinzafari.github.framework.todoapp.feature_note.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
    @created in 07/12/2021 - 4:14 PM
    @project Todo App
    @author Hossein Zafari
    @email  hosseinzafari2000@gmail.com
*/

@Entity(tableName = "note_tb")
data class NoteEntity(
    @PrimaryKey val uid: Int?,
    val title: String ,
    val text: String ,
    val timestamp: Long ,
    val color: Long
)
