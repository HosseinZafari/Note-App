package com.hosseinzafari.github.framework.todoapp.feature_note.domain.model

import com.hosseinzafari.github.framework.todoapp.ui.theme.*

/*

@created in 07/12/2021 - 4:45 PM
@project Todo App
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

data class NoteModel(
    val id: Int?,
    val title: String,
    val text: String,
    val timeStamp: Long,
    val color: Long
) {
    companion object {
        val colors = listOf(Purple500 , Purple700 , Purple200 , Orange200 , Teal200 , Yellow200 )
    }
}


class NoteEmptyException(msg: String) : Exception(msg)