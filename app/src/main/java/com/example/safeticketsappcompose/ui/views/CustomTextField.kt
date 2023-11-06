package com.example.safeticketsappcompose.ui.views

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextField(
    text: String,
) {
    val textStyle = TextStyle(fontSize = 20.sp)
    BasicTextField(
        value = text,
        onValueChange = {  },
        textStyle = textStyle,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
    )
}
