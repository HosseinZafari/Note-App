package com.hosseinzafari.github.framework.todoapp.feature_note.presentation.add_edit_note

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hosseinzafari.github.framework.todoapp.feature_note.domain.model.NoteModel
import com.hosseinzafari.github.framework.todoapp.feature_note.presentation.add_edit_note.components.TransparentHintTextField
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/*

@created in 08/12/2021 - 6:08 PM
@project Todo App
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

@Composable
fun AddEditNoteScreen(
    navController: NavController,
    noteColor: Int,
    viewModel: AddEditNoteViewModel = hiltViewModel()
) {

    val noteTitle = viewModel.titleState.value
    val noteText = viewModel.textState.value

    val scaffoldState = rememberScaffoldState()
    val animatableState = remember {
        Animatable(Color(if (noteColor != -1) noteColor else viewModel.colorState.value))
    }

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.sharedEventFlow.collectLatest { event ->
            when (event) {
                is AddEditNoteViewModel.UiState.Save -> {
                    navController.navigateUp()
                }
                is AddEditNoteViewModel.UiState.ShowSnack -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                    )
                }
            }
        }
    }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {

                    },
                    backgroundColor = MaterialTheme.colors.background

                ) {
                    IconButton(onClick = {
                        viewModel.onEvent(AddEditNoteEvent.SaveNote)
                    }) {
                        Icon(imageVector = Icons.Default.Save, contentDescription = "Save Note")
                    }
                }
            },
            scaffoldState = scaffoldState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(animatableState.value)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    NoteModel.colors.forEach { color ->
                        val colorInt = color.toArgb()

                        Box(modifier = Modifier
                            .size(50.dp)
                            .shadow(15.dp, shape = CircleShape)
                            .clip(CircleShape)
                            .background(color)
                            .border(
                                width = 3.dp,
                                color = if (viewModel.colorState.value == colorInt) Color.Black else color,
                                shape = CircleShape
                            )
                            .clickable {
                                coroutineScope.launch {
                                    animatableState.animateTo(
                                        targetValue = color,
                                        animationSpec = tween(
                                            durationMillis = 500,
                                        )
                                    )

                                    viewModel.onEvent(AddEditNoteEvent.ChangeColor(color))
                                }
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                TransparentHintTextField(
                    text = noteTitle.text,
                    hint = noteTitle.hint,
                    onValueChange = {
                        viewModel.onEvent(AddEditNoteEvent.EnteredTitleEditText(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(AddEditNoteEvent.OnChangeFocusTitle(it))
                    },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    isHintVisible = noteTitle.isHintVisible,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.h5
                )

                Spacer(modifier = Modifier.height(16.dp))
                TransparentHintTextField(
                    text = noteText.text,
                    hint = noteText.hint,
                    onValueChange = {
                        viewModel.onEvent(AddEditNoteEvent.EnteredTextEditText(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(AddEditNoteEvent.OnChangeFocusText(it))
                    },
                    singleLine = false,
                    isHintVisible = noteText.isHintVisible,
                    textStyle = MaterialTheme.typography.body1,
                    modifier = Modifier.fillMaxHeight().fillMaxWidth().padding(horizontal = 16.dp)
                )


            }
        }
    }
}