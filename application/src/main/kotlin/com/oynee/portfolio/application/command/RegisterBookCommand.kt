package com.oynee.portfolio.application.command

import java.time.LocalDate

data class RegisterBookCommand(
    val id: String,
    val title: String,
    val author: String,
    val publishedDate: LocalDate,
) {
    init {
        require(id.isNotBlank()) { "Book id must not be blank." }
        require(publishedDate.year >= 1450) {
            "Published date is too early (before printing press era)."
        }
    }
}