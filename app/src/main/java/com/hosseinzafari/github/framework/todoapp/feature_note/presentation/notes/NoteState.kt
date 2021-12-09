package com.hosseinzafari.github.framework.todoapp.feature_note.presentation.notes

import com.hosseinzafari.github.framework.todoapp.feature_note.domain.model.NoteModel
import com.hosseinzafari.github.framework.todoapp.feature_note.domain.utils.NoteOrder
import com.hosseinzafari.github.framework.todoapp.feature_note.domain.utils.OrderType

/*

@created in 07/12/2021 - 8:39 PM
@project Todo App
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

data class NoteState(
    val notes: List<NoteModel?> = emptyList() ,
    val noteOrder: NoteOrder = NoteOrder.TimestampOrder(OrderType.Descending) ,
    val isOrderSectionVisible: Boolean = false
)