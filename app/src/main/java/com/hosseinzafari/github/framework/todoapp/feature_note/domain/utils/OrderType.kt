package com.hosseinzafari.github.framework.todoapp.feature_note.domain.utils

/*

@created in 07/12/2021 - 7:16 PM
@project Todo App
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

sealed class OrderType {
    object Descending : OrderType()
    object Ascending : OrderType()
}