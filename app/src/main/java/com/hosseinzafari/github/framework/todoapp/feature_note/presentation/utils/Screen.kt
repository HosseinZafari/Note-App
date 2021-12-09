package com.hosseinzafari.github.framework.todoapp.feature_note.presentation.utils

/*

@created in 08/12/2021 - 7:57 PM
@project Todo App
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

sealed class Screen(val route: String) {
    object AddEditNoteScreen : Screen("add_edit_note")
    object NotesScreen : Screen("notes")
}