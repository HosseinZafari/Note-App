package com.hosseinzafari.github.framework.todoapp.feature_note.data.entity

import com.hosseinzafari.github.framework.todoapp.feature_note.domain.model.NoteModel
import com.hosseinzafari.github.framework.todoapp.feature_note.domain.utils.DomainMapperList

/*

@created in 07/12/2021 - 5:24 PM
@project Todo App
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class NoteMapper : DomainMapperList<NoteEntity, NoteModel> {
    override fun mapFromEntity(entity:  NoteEntity?): NoteModel? {
        return if(entity != null)
            NoteModel(
            id = entity.uid,
            title = entity.title ,
            text = entity.text,
            timeStamp = entity.timestamp,
            color = entity.color
        ) else null
    }

    override fun mapFromModel(model: NoteModel?): NoteEntity? {
        return if(model != null)
            NoteEntity(
            uid = model.id,
            title = model.title ,
            text = model.text,
            timestamp= model.timeStamp,
            color = model.color
        ) else return null
    }

    override fun mapFromEntityList(entities: List<NoteEntity?>): List<NoteModel?> {
        return entities.map { mapFromEntity(it) }
    }

    override fun mapFromModelList(models: List<NoteModel?>): List<NoteEntity?> {
        return models.map { mapFromModel(it) }
    }


}