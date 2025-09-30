package com.oynee.portfolio.adapter.persistence.adapter

import com.oynee.portfolio.adapter.persistence.mapper.BookEntityMapper
import com.oynee.portfolio.adapter.persistence.repository.BookRepository
import com.oynee.portfolio.application.port.output.persistence.BookCommandPort
import com.oynee.portfolio.application.port.output.persistence.BookQueryPort
import com.oynee.portfolio.domain.Book
import org.springframework.stereotype.Component
import kotlin.jvm.optionals.getOrElse

@Component
class BookPersistenceAdapter(
    private val bookRepository: BookRepository,
    private val bookEntityMapper: BookEntityMapper

) : BookQueryPort, BookCommandPort {
    override fun saveBook(book: Book) {
        return bookEntityMapper.toBookEntity(book)
            .let { bookEntity -> bookRepository.save(bookEntity) }
    }

    override fun deleteBook(id: String) {
        bookRepository.findById(id)
            .getOrElse { return }

        bookRepository.deleteById(id)
    }

    override fun findBook(id: String): Book {
        return bookRepository.findById(id)
            .getOrElse { throw NoSuchElementException("Can not find book : $id") }
            .let { bookEntity -> bookEntityMapper.toBook(bookEntity) }
    }

    override fun existsById(id: String): Boolean {
        return bookRepository.existsById(id)
    }
}