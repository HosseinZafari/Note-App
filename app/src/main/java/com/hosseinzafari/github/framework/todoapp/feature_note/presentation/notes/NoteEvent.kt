package com.hosseinzafari.github.framework.todoapp.feature_note.presentation.notes

import com.hosseinzafari.github.framework.todoapp.feature_note.domain.model.NoteModel
import com.hosseinzafari.github.framework.todoapp.feature_note.domain.utils.NoteOrder

/*

@created in 07/12/2021 - 8:46 PM
@project Todo App
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

sealed class NoteEvent {
    data class Order(val noteOrder: NoteOrder): NoteEvent()
    data class Delete(val note: NoteModel): NoteEvent()

    object RestoreNote : NoteEvent()
    object Toggle : NoteEvent()

}
