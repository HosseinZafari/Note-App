package com.hosseinzafari.github.framework.todoapp.feature_note.presentation.add_edit_note

import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color

/*

@created in 08/12/2021 - 5:21 PM
@project Todo App
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

sealed class AddEditNoteEvent {
    data class ChangeColor(val color: Color) : AddEditNoteEvent()
    data class EnteredTitleEditText(val value: String) : AddEditNoteEvent()
    data class EnteredTextEditText(val value: String) : AddEditNoteEvent()
    data class OnChangeFocusTitle(val focusState: FocusState): AddEditNoteEvent()
    data class OnChangeFocusText(val focusState: FocusState): AddEditNoteEvent()

    object SaveNote : AddEditNoteEvent()
}