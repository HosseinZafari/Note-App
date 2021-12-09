package com.hosseinzafari.github.framework.todoapp.feature_note.domain.utils

/*

@created in 07/12/2021 - 5:04 PM
@project Todo App
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

interface DomainMapper<Entity , Model> {

    fun mapFromEntity(entity: Entity?) : Model?
    fun mapFromModel(model: Model?) : Entity?
}