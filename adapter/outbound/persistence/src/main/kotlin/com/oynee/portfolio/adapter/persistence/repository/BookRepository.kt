package com.oynee.portfolio.adapter.persistence.repository

import com.oynee.portfolio.adapter.persistence.entity.BookEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository: JpaRepository<BookEntity, String>