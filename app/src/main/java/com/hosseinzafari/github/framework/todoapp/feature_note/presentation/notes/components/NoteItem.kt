package com.hosseinzafari.github.framework.todoapp.feature_note.presentation.notes.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import com.hosseinzafari.github.framework.todoapp.feature_note.domain.model.NoteModel

/*

@created in 07/12/2021 - 11:01 PM
@project Todo App
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/


@Composable
fun NoteItem(
    noteModel: NoteModel,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp,
    cutCornerSize: Dp = 30.dp,
    onDeleteClick: (NoteModel) -> Unit
) {

    Box(
        modifier = modifier
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val path = Path().apply {
                lineTo(size.width - cornerRadius.toPx(), 0f)
                lineTo(size.width, cornerRadius.toPx())
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }

            clipPath(path) {
                drawRoundRect(
                    color = Color(color = noteModel.color),
                    size = size,
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )

                drawRoundRect(
                    color = Color(
                        ColorUtils.blendARGB(
                            noteModel.color.toInt(),
                            0x000000,
                            0.2f
                        )
                    ),

                    topLeft = Offset(
                        size.width - cornerRadius.toPx(),
                        -170f
                    ),

                    size = Size(
                        cutCornerSize.toPx() + 150f,
                        cutCornerSize.toPx() + 150f
                    ),

                    cornerRadius = CornerRadius(cornerRadius.toPx()),
                )
            }

        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(end = 32.dp),
        ) {
            Text(
                text = noteModel.title,
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = noteModel.text,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface,
                maxLines = 10,
                overflow = TextOverflow.Ellipsis,
            )

        }

        IconButton(
            onClick = {
                onDeleteClick(noteModel)
            },
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete note"
            )
        }
    }

}


