package com.oynee.portfolio.adapter.api.response

import java.time.LocalDate

class BookResponse(
    val id: String,
    val title: String,
    val author: String,
    val publishedDate: LocalDate
)