package com.example.safeticketsappcompose.ui.Register

import CustomStyledTextField
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.safeticketsappcompose.R

class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)


        val composeView = view.findViewById<ComposeView>(R.id.composeView)
        composeView.setContent {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                var username by remember { mutableStateOf("") }
                var email by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }
                var name by remember { mutableStateOf("") }
                var lastname by remember { mutableStateOf("") }
                var phone by remember { mutableStateOf("") }
                var rePassword by remember { mutableStateOf("") }

                CustomStyledTextField(
                    value = username,
                    onValueChange = { username = it },
                    placeholder = "Логин",
                    modifier = Modifier
                        .width(450.dp)
                        .padding(vertical = 10.dp)
                )

                CustomStyledTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = "Почта",
                    modifier = Modifier
                        .width(450.dp)
                        .padding(vertical = 10.dp),
                    keyboardType = KeyboardType.Email
                )
                CustomStyledTextField(
                    value = name,
                    onValueChange = { name = it },
                    placeholder = "Имя",
                    modifier = Modifier
                        .width(450.dp)
                        .padding(vertical = 10.dp)
                )
                CustomStyledTextField(
                    value = lastname,
                    onValueChange = { lastname = it },
                    placeholder = "Фамилия",
                    modifier = Modifier
                        .width(450.dp)
                        .padding(vertical = 10.dp),
                    keyboardType = KeyboardType.Phone
                )
                CustomStyledTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    placeholder = "Телефон",
                    modifier = Modifier
                        .width(450.dp)
                        .padding(vertical = 10.dp)
                )
                CustomStyledTextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = "Пароль",
                    modifier = Modifier
                        .width(450.dp)
                        .padding(vertical = 10.dp),
                    keyboardType = KeyboardType.Password
                )
                CustomStyledTextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = "Пароль",
                    modifier = Modifier
                        .width(450.dp)
                        .padding(vertical = 10.dp),
                    keyboardType = KeyboardType.Password
                )


                Button(onClick = { /* Действие при нажатии кнопки авторизации */ }) {
                    Text(text = "Register")
                }

            }
        }

        return view
    }

    override fun onResume() {
        (getActivity() as AppCompatActivity)?.getSupportActionBar()?.hide()
        super.onResume()
    }
}