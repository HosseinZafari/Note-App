package com.hosseinzafari.github.framework.todoapp.feature_note.data.data_source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hosseinzafari.github.framework.todoapp.feature_note.data.data_source.db.dao.NoteDao
import com.hosseinzafari.github.framework.todoapp.feature_note.data.entity.NoteEntity


@Database(
    entities = [NoteEntity::class],
    version = 1
)
abstract class NoteDatabase : RoomDatabase() {

    abstract val noteDao: NoteDao

}