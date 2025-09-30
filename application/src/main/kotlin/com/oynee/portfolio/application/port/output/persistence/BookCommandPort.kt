package com.oynee.portfolio.application.port.output.persistence

import com.oynee.portfolio.domain.Book

interface BookCommandPort {

    fun saveBook(book: Book)

    fun deleteBook(id: String)

}