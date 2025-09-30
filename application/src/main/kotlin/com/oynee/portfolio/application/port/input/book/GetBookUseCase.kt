package com.oynee.portfolio.application.port.input.book

import com.oynee.portfolio.domain.Book

interface GetBookUseCase {

    fun getBook(id: String): Book

}