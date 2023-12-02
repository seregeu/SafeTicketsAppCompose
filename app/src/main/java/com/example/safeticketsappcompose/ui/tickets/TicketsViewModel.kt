package com.example.safeticketsappcompose.ui.tickets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.safeticketsappcompose.models.local_models.Ticket
import com.example.safeticketsappcompose.repository.Repository
import com.example.safeticketsappcompose.repository.RepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import kotlin.random.Random

class TicketsViewModel : ViewModel() {
    private val repository: Repository = RepositoryImpl()

    private val tickets = getRandomTicketList(15)

    fun getRandomLocalDate(): LocalDate {
        // Генерация случайной даты в интервале от сегодняшней даты до 30 дней вперед
        val today = LocalDate.now()
        val randomDays = Random.nextInt(30)
        return today.plusDays(randomDays.toLong())
    }

    fun getRandomRussianCity(): String {
        val russianCities = listOf(
            "Москва", "Санкт-Петербург", "Новосибирск", "Екатеринбург", "Нижний Новгород",
            "Казань", "Челябинск", "Омск", "Самара", "Ростов-на-Дону",
            "Уфа", "Красноярск", "Пермь", "Воронеж", "Волгоград",
            "Краснодар", "Саратов", "Тюмень", "Тольятти", "Ижевск",
            "Барнаул", "Ульяновск", "Иркутск", "Хабаровск", "Ярославль",
            "Владивосток", "Махачкала", "Томск", "Оренбург"
        )

        return russianCities.random()
    }

    fun getRandomTicketList(numTickets: Int): List<Ticket> {
        val ticketList = mutableListOf<Ticket>()

        repeat(numTickets) {
            val cityFrom = getRandomRussianCity()
            val cityTo = getRandomRussianCity()
            val departureDate = getRandomLocalDate()
            val arrivalDate = departureDate.plusDays(Random.nextInt(10).toLong()) // Билет действителен 1-10 дней

            val ticket = Ticket(cityFrom, cityTo, departureDate, arrivalDate)
            ticketList.add(ticket)
        }

        return ticketList
    }
    fun getTickets(): List<Ticket> {
        return tickets
    }

    fun buyTicket() {
        CoroutineScope(Dispatchers.IO).launch {
            repository.buyTicket()
        }
    }
}