package com.example.safeticketsappcompose.ui.search

import androidx.lifecycle.ViewModel
import com.example.safeticketsappcompose.repository.RepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val repository = RepositoryImpl()
    fun searchTickets(){
        CoroutineScope(Dispatchers.IO).launch {
            repository.buyTicket()
        }
    }
}