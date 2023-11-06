package com.example.safeticketsappcompose.ui.search

import CustomStyledTextField
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import com.example.safeticketsappcompose.R
import com.example.safeticketsappcompose.ui.views.CustomTextField
import com.example.safeticketsappcompose.ui.views.DatePicker
import com.example.safeticketsappcompose.ui.views.SwapTextFieldsView

class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        val composeView = view.findViewById<ComposeView>(R.id.composeView)

        composeView.setContent {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomTextField(text = "Выберите город")
                SwapTextFieldsView()
                CustomTextField(text = "Выберите даты")
                Row(horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp, vertical = 4.dp)
                   ) {
                        DatePicker({})
                        DatePicker({})
                }
                Button(onClick = {
                },
                    modifier = Modifier.padding(top = 8.dp)) {
                    Text(text = "Искать")
                }

            }
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
    }

    override fun onResume() {
        (getActivity() as AppCompatActivity)?.getSupportActionBar()?.show()
        super.onResume()
    }
}