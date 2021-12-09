package com.hosseinzafari.github.framework.todoapp.feature_note.domain.use_case

import com.hosseinzafari.github.framework.todoapp.feature_note.domain.model.NoteEmptyException
import com.hosseinzafari.github.framework.todoapp.feature_note.domain.model.NoteModel
import com.hosseinzafari.github.framework.todoapp.feature_note.domain.repository.NoteRepository

/*

@created in 07/12/2021 - 9:03 PM
@project Todo App
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class AddNote(
    val repository: NoteRepository
) {

    @Throws(NoteEmptyException::class)
    suspend operator fun invoke(noteModel: NoteModel) {
        if (noteModel.title.isBlank()) {
            throw NoteEmptyException("not allowed empty string in title")
        }

        if (noteModel.text.isBlank()) {
            throw NoteEmptyException("not allowed empty string in text")
        }

        repository.addNote(noteModel)
    }

}