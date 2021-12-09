package com.hosseinzafari.github.framework.todoapp.feature_note.presentation.notes.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hosseinzafari.github.framework.todoapp.feature_note.domain.utils.NoteOrder
import com.hosseinzafari.github.framework.todoapp.feature_note.domain.utils.OrderType

/*

@created in 07/12/2021 - 10:27 PM
@project Todo App
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    noteOrder: NoteOrder = NoteOrder.TimestampOrder(OrderType.Descending),
    onOrderChange: (NoteOrder) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(
                text = "عنوان",
                selected = noteOrder is NoteOrder.TitleOrder,
                onSelect = {onOrderChange(NoteOrder.TitleOrder(noteOrder.orderType))}
            )

            Spacer(modifier = Modifier.width(8.dp))

            DefaultRadioButton(
                text = "زمان",
                selected = noteOrder is NoteOrder.TimestampOrder,
                onSelect = {onOrderChange(NoteOrder.TimestampOrder(noteOrder.orderType))}
            )

            Spacer(modifier = Modifier.width(8.dp))

            DefaultRadioButton(
                text = "رنگ",
                selected = noteOrder is NoteOrder.Color,
                onSelect = {onOrderChange(NoteOrder.Color(noteOrder.orderType))}
            )

        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "از آخر",
                selected = noteOrder.orderType is OrderType.Descending
            ) {
                onOrderChange(noteOrder.copy(OrderType.Descending))
            }

            Spacer(modifier = Modifier.width(8.dp))

            DefaultRadioButton(
                text = "از اول",
                selected = noteOrder.orderType is OrderType.Ascending
            ) {
                onOrderChange(noteOrder.copy(OrderType.Ascending))
            }

        }
    }
}