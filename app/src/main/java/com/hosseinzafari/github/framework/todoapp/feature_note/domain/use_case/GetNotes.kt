package com.hosseinzafari.github.framework.todoapp.feature_note.domain.use_case

import com.hosseinzafari.github.framework.todoapp.feature_note.domain.model.NoteModel
import com.hosseinzafari.github.framework.todoapp.feature_note.domain.repository.NoteRepository
import com.hosseinzafari.github.framework.todoapp.feature_note.domain.utils.NoteOrder
import com.hosseinzafari.github.framework.todoapp.feature_note.domain.utils.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/*

@created in 07/12/2021 - 7:13 PM
@project Todo App
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/


class GetNotes (
    val noteRepository: NoteRepository
){

    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.TimestampOrder(OrderType.Descending)
    ): Flow<List<NoteModel?>> {
        return noteRepository.getNotes().map { notes ->
            when(noteOrder.orderType) {
                is OrderType.Descending -> {
                    when (noteOrder) {
                        is NoteOrder.TimestampOrder -> notes.sortedByDescending { it?.timeStamp }
                        is NoteOrder.TitleOrder -> notes.sortedByDescending { it?.title }
                        is NoteOrder.Color -> notes.sortedByDescending { it?.color }
                    }
                }

                is OrderType.Ascending -> {
                    when (noteOrder) {
                        is NoteOrder.TimestampOrder -> notes.sortedBy { it?.timeStamp }
                        is NoteOrder.TitleOrder -> notes.sortedBy { it?.title }
                        is NoteOrder.Color -> notes.sortedBy { it?.color  }
                    }
                }
            }
        }
    }
}