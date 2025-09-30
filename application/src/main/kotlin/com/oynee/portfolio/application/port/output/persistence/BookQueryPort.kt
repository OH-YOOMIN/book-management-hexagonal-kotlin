package com.oynee.portfolio.application.port.output.persistence

import com.oynee.portfolio.domain.Book

interface BookQueryPort {

    fun findBook(id: String): Book

    fun existsById(id: String): Boolean

}