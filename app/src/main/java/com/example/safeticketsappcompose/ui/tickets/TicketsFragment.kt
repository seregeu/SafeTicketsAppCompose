package com.example.safeticketsappcompose.ui.tickets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.safeticketsappcompose.R
import com.example.safeticketsappcompose.databinding.FragmentGalleryBinding
import com.example.safeticketsappcompose.models.local_models.Ticket
import com.example.safeticketsappcompose.ui.search.SearchFragment
import com.example.safeticketsappcompose.ui.search.SearchViewModel
import com.example.safeticketsappcompose.ui.views.CustomTextField
import com.example.safeticketsappcompose.ui.views.DatePicker
import com.example.safeticketsappcompose.ui.views.SwapTextFieldsView
import com.example.safeticketsappcompose.ui.views.TrainTicket

class TicketsFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var viewModel: TicketsViewModel

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
                TicketList(viewModel.getTickets())
            }
        }
        return view
    }

    @Composable
    fun TicketList(tickets: List<Ticket>) {
        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            items(tickets) {
                TrainTicket(ticket = it,  onClick = {
                    viewModel.buyTicket()
                    Toast.makeText(context, "Билет куплен", Toast.LENGTH_SHORT).show()
                })
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TicketsViewModel::class.java)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}