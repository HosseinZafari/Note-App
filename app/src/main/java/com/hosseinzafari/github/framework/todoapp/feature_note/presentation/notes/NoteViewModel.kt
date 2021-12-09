package com.hosseinzafari.github.framework.todoapp.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hosseinzafari.github.framework.todoapp.feature_note.domain.model.NoteModel
import com.hosseinzafari.github.framework.todoapp.feature_note.domain.repository.NoteRepository
import com.hosseinzafari.github.framework.todoapp.feature_note.domain.use_case.NoteUseCases
import com.hosseinzafari.github.framework.todoapp.feature_note.domain.utils.NoteOrder
import com.hosseinzafari.github.framework.todoapp.feature_note.domain.utils.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NoteViewModel @Inject constructor(
    private val useCases: NoteUseCases,
    private val repository: NoteRepository
) : ViewModel() {

    private var lastDeletedNote: NoteModel? = null
    private var getNotesJob: Job? = null
    private val _state = mutableStateOf(NoteState())
    val state : State<NoteState> = _state


    init {
        getNotes(NoteOrder.TimestampOrder(OrderType.Descending))
    }

    fun onEvent(event: NoteEvent) {
        when (event) {
            is NoteEvent.Order -> {
                if (state.value.noteOrder::class == event.noteOrder::class &&
                    state.value.noteOrder.orderType::class == event.noteOrder.orderType::class
                ) return

                _state.value = state.value.copy(noteOrder = event.noteOrder)
                getNotes(event.noteOrder)
            }

            is NoteEvent.Delete -> {
                viewModelScope.launch {
                    useCases.deleteNote(event.note)
                    lastDeletedNote = event.note
                }
            }

            is NoteEvent.RestoreNote -> {
                viewModelScope.launch {
                    useCases.addNote(lastDeletedNote ?: return@launch)
                    lastDeletedNote = null
                }
            }

            is NoteEvent.Toggle -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder) {
        getNotesJob?.cancel()

        getNotesJob = useCases.getNotes(noteOrder).onEach {
            _state.value = state.value.copy(
                notes = it ,
                noteOrder = noteOrder
            )
        }.launchIn(viewModelScope)
    }


}