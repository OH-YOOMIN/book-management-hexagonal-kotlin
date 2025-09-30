package com.oynee.portfolio.adapter.api.request

import jakarta.validation.constraints.NotBlank
import java.time.LocalDate

data class RegisterBookRequest(
    val id: String,
    @field:NotBlank(message = "id must not be blank")
    val title: String,
    val author: String,
    val publishedDate: LocalDate,
)