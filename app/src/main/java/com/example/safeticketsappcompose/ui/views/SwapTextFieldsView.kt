package com.example.safeticketsappcompose.ui.views

import CustomStyledTextField
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SwapTextFieldsView() {
    var text1 by remember { mutableStateOf("") }
    var text2 by remember { mutableStateOf("") }

    Row(
        modifier = Modifier.height(150.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    val temp = text1
                    text1 = text2
                    text2 = temp
                },
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(text = "⇋",
                    fontSize = 20.sp)
            }
        }

        Column(
        ) {
            CustomStyledTextField(
                value = text1,
                onValueChange = { text1 = it },
                placeholder = "Город отбытия",
                modifier = Modifier
                    .width(450.dp)
                    .padding(vertical = 10.dp)
            )

            CustomStyledTextField(
                value = text2,
                onValueChange = { text2 = it },
                placeholder = "Город прибытия",
                modifier = Modifier
                    .width(450.dp)
                    .padding(vertical = 10.dp)
            )
        }
    }
}