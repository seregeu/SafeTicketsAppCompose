package com.example.safeticketsappcompose.ui.Login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.example.safeticketsappcompose.MainActivity
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

                BasicTextField(
                    value = username,
                    onValueChange = { username = it },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                    decorationBox = { innerTextField ->
                        Box(
                            Modifier
                                .background(Color.LightGray, RoundedCornerShape(percent = 30))
                                .padding(16.dp)
                        ) {

                            if (username.isEmpty()) {
                                Text("Логин",
                                    fontWeight = FontWeight.Normal,
                                    color = Color.Gray)
                            }
                            innerTextField()
                        }
                    },
                    modifier = Modifier.width(450.dp).padding(vertical = 10.dp)
                )


                BasicTextField(
                    value = password,
                    onValueChange = { password = it },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                    decorationBox = { innerTextField ->
                        Box(
                            Modifier
                                .background(Color.LightGray, RoundedCornerShape(percent = 30))
                                .padding(16.dp)
                        ) {

                            if (password.isEmpty()) {
                                Text(
                                    text = "Пароль",
                                    fontWeight = FontWeight.Normal,
                                    color = Color.Gray
                                )
                            }
                            innerTextField()
                        }
                    },
                    modifier = Modifier.width(450.dp)
                )

                Button(onClick = { /* Действие при нажатии кнопки авторизации */ }) {
                    Text(text = "Log In")
                }


                Button(
                    onClick = { /* Действие при нажатии кнопки для регистрации */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                ) {
                    Text(text = "Don't have an account?",
                        color = Color.LightGray
                    )
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
