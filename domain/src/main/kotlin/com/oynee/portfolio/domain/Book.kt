package com.oynee.portfolio.domain

import java.time.LocalDate

data class Book(
    val id: String,
    val title: String,
    val author: String,
    val publishedDate: LocalDate,
)