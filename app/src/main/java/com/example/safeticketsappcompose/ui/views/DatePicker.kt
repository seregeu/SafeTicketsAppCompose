package com.example.safeticketsappcompose.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate


@Composable
fun DatePicker(onValueChange: (LocalDate) -> Unit) {
    val dialogState = rememberMaterialDialogState()
    var dateState by remember { mutableStateOf<LocalDate>(LocalDate.now())}
    Box(
        Modifier
            .background(Color.LightGray, RoundedCornerShape(percent = 30))
            .padding(16.dp)
    ) {
        ClickableText(
            text = AnnotatedString(dateState.toString()),
            modifier = Modifier.clickable {
                dialogState.show()
            },
            onClick = { offset ->
                dialogState.show()
            }
        )
    }
    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton("Принять")
            negativeButton("Отмена")
        },
        shape = RoundedCornerShape(20.dp)
    ) {
        datepicker { date ->
            dateState = date
        }
    }
}