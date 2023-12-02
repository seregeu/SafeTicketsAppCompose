package com.example.safeticketsappcompose.models.local_models

import java.time.LocalDate
import java.util.Date


data class Ticket(
    val cityFrom: String,
    val cityTo: String,
    val departureDate: LocalDate,
    val arrivalDate: LocalDate
)
