package com.example.safeticketsappcompose.ui.Login

import CustomStyledTextField
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.safeticketsappcompose.R

class LoginFragment : Fragment() {

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
                var password by remember { mutableStateOf("") }

                CustomStyledTextField(
                    value = username,
                    onValueChange = { username = it },
                    placeholder = "Логин",
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

                Button(onClick = { /* Действие при нажатии кнопки авторизации */ }) {
                    Text(text = "Log In")
                }


                Button(
                    onClick = { /* Действие при нажатии кнопки для регистрации */
                        findNavController().navigate(R.id.registerFragment)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                ) {
                    Text(
                        text = "Don't have an account?",
                        color = Color.LightGray
                    )
                }
            }
        }

        return view
    }

    override fun onResume() {
        (getActivity() as AppCompatActivity).getSupportActionBar()?.hide()
        super.onResume()
    }
}
