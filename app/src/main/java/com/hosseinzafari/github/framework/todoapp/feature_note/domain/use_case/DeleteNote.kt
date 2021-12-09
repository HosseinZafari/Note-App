package com.hosseinzafari.github.framework.todoapp.feature_note.domain.use_case

import com.hosseinzafari.github.framework.todoapp.feature_note.domain.model.NoteModel
import com.hosseinzafari.github.framework.todoapp.feature_note.domain.repository.NoteRepository

/*

@created in 07/12/2021 - 7:28 PM
@project Todo App
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class DeleteNote (
    val noteRepository: NoteRepository
) {

    suspend operator fun invoke(noteModel: NoteModel) {
        noteRepository.deleteNote(noteModel)
    }

}