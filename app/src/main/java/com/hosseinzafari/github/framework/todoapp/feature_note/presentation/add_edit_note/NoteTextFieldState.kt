package com.hosseinzafari.github.framework.todoapp.feature_note.presentation.add_edit_note

/*

@created in 08/12/2021 - 3:29 PM
@project Todo App
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

data class NoteTextFieldState(
    val hint: String = "",
    val text: String = "",
    val isHintVisible: Boolean = true
)