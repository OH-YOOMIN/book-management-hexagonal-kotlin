package com.oynee.portfolio.adapter.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "book")
class BookEntity(
    @Id
    @Column(name = "book_id")
    val id: String,

    val title: String,
    val author: String,
    val publishedDate: LocalDate
)