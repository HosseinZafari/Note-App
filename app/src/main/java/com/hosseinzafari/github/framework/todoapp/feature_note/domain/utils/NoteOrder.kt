package com.hosseinzafari.github.framework.todoapp.feature_note.domain.utils

/*

@created in 07/12/2021 - 7:17 PM
@project Todo App
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

sealed class NoteOrder(
    val orderType: OrderType
) {
    class TitleOrder(orderType: OrderType) : NoteOrder(orderType)
    class TimestampOrder(orderType: OrderType) : NoteOrder(orderType)
    class Color(orderType: OrderType) : NoteOrder(orderType)


    fun copy(orderType: OrderType) = when (this) {
        is TitleOrder -> TitleOrder(orderType)
        is TimestampOrder -> TimestampOrder(orderType)
        is Color -> Color(orderType)
    }
}
