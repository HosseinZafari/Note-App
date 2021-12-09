package com.hosseinzafari.github.framework.todoapp.feature_note.domain.use_case

import com.hosseinzafari.github.framework.todoapp.feature_note.domain.model.NoteModel
import com.hosseinzafari.github.framework.todoapp.feature_note.domain.repository.NoteRepository

/*

@created in 08/12/2021 - 3:00 PM
@project Todo App
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class GetNote(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(id: Int): NoteModel? {
       return repository.getNoteById(id)
    }

}