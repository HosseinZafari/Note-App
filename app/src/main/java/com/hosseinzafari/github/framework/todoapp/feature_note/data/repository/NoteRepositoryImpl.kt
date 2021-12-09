package com.hosseinzafari.github.framework.todoapp.feature_note.data.repository

import com.hosseinzafari.github.framework.todoapp.feature_note.data.data_source.db.dao.NoteDao
import com.hosseinzafari.github.framework.todoapp.feature_note.data.entity.NoteEntity
import com.hosseinzafari.github.framework.todoapp.feature_note.domain.model.NoteModel
import com.hosseinzafari.github.framework.todoapp.feature_note.domain.repository.NoteRepository
import com.hosseinzafari.github.framework.todoapp.feature_note.domain.utils.DomainMapperList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/*

@created in 07/12/2021 - 4:43 PM
@project Todo App
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class NoteRepositoryImpl(
    val noteDao: NoteDao ,
    val domainMapper: DomainMapperList<NoteEntity , NoteModel>
) : NoteRepository {

    override fun getNotes(): Flow<List<NoteModel?>> {
        return noteDao.getNotes().map {
            domainMapper.mapFromEntityList(it)
        }
    }

    override suspend fun getNoteById(id: Int): NoteModel? {
        return domainMapper.mapFromEntity(noteDao.getNoteById(id))
    }

    override suspend fun addNote(noteModel: NoteModel) {
        return noteDao.addNote(domainMapper.mapFromModel(noteModel)!!)
    }

    override suspend fun deleteNote(noteModel: NoteModel) {
        return noteDao.deleteNote(domainMapper.mapFromModel(noteModel)!!)
    }
}