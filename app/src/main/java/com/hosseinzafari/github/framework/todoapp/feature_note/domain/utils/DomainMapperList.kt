package com.hosseinzafari.github.framework.todoapp.feature_note.domain.utils

/*

@created in 07/12/2021 - 5:33 PM
@project Todo App
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

interface DomainMapperList<Entity , Model> : DomainMapper<Entity , Model> {

    fun mapFromEntityList(entities: List<Entity?>): List<Model?>
    fun mapFromModelList(models: List<Model?>): List<Entity?>
}