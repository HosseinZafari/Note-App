package com.hosseinzafari.github.framework.todoapp.feature_note.presentation.add_edit_note

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hosseinzafari.github.framework.todoapp.feature_note.domain.model.NoteEmptyException
import com.hosseinzafari.github.framework.todoapp.feature_note.domain.model.NoteModel
import com.hosseinzafari.github.framework.todoapp.feature_note.domain.use_case.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    val useCases: NoteUseCases,
    val stateHandle: SavedStateHandle
) : ViewModel() {

    /*
    * title / text / color /
    * */

    private val _titleState = mutableStateOf(NoteTextFieldState(
        hint = "لطفا عنوان را وارد کنید"
    ))
    val titleState: State<NoteTextFieldState> = _titleState


    private val _textState = mutableStateOf(NoteTextFieldState(
        hint = "چه کاری میخواهید بکنید ؟"
    ))
    val textState: State<NoteTextFieldState> = _textState


    private val _colorState = mutableStateOf(NoteModel.colors.random().toArgb())
    val colorState: State<Int> = _colorState

    private val _eventFlow = MutableSharedFlow<UiState>()
    val sharedEventFlow = _eventFlow.asSharedFlow()

    private var currentNoteId: Int? = null

    init {
        stateHandle.get<Int>("noteId")?.let { id ->
            if(id != -1 ) {
                viewModelScope.launch {
                    useCases.getNote(id)?.also {
                        currentNoteId = it.id
                        _titleState.value = titleState.value.copy(
                            text = it.title,
                            isHintVisible = false
                        )
                        _textState.value = textState.value.copy(
                            text = it.text,
                            isHintVisible = false
                        )
                        _colorState.value = it.color.toInt()
                    }
                }
            }
        }
    }


    fun onEvent(event: AddEditNoteEvent) {
        when (event) {
            is AddEditNoteEvent.ChangeColor -> {
                _colorState.value = event.color.toArgb()
            }

            is AddEditNoteEvent.OnChangeFocusText -> {
                _textState.value = textState.value.copy(
                    isHintVisible = !event.focusState.isFocused && textState.value.text.isBlank()
                )
            }

            is AddEditNoteEvent.OnChangeFocusTitle -> {
                _titleState.value = titleState.value.copy(
                    isHintVisible = !event.focusState.isFocused && titleState.value.text.isBlank()
                )
            }

            is AddEditNoteEvent.EnteredTextEditText -> {
                _textState.value = textState.value.copy(
                    text = event.value
                )
            }

            is AddEditNoteEvent.EnteredTitleEditText -> {
                _titleState.value = titleState.value.copy(
                    text = event.value
                )
            }

            is AddEditNoteEvent.SaveNote -> {
                viewModelScope.launch {
                    Log.i("Test" , "save note evented")
                    try {
                        useCases.addNote(
                            NoteModel(
                            id = currentNoteId ,
                            title = titleState.value.text ,
                            text  = textState.value.text ,
                            timeStamp = System.currentTimeMillis(),
                            color = colorState.value.toLong()
                        ))

                        _eventFlow.emit(
                            UiState.Save
                        )
                    } catch (err: NoteEmptyException) {
                        _eventFlow.emit(
                            UiState.ShowSnack(
                                message = err.message ?: "نمی توانیم یادداشتی ایجاد کنیم"
                            )
                        )
                    }
                }
            }
        }

    }

    sealed class UiState {
        data class ShowSnack(val message: String): UiState()
        object Save : UiState()
    }
}