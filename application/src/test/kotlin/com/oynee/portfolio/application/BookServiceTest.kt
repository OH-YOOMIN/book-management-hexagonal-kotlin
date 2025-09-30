package com.oynee.portfolio.application

import com.oynee.portfolio.application.command.ModifyBookCommand
import com.oynee.portfolio.application.command.RegisterBookCommand
import com.oynee.portfolio.application.mapper.BookMapper
import com.oynee.portfolio.application.port.output.persistence.BookCommandPort
import com.oynee.portfolio.application.port.output.persistence.BookQueryPort
import com.oynee.portfolio.application.service.BookService
import com.oynee.portfolio.domain.Book
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate



class BookServiceTest {
    private lateinit var bookCommandPort: BookCommandPort
    private lateinit var bookQueryPort: BookQueryPort
    private lateinit var bookMapper: BookMapper

    private lateinit var bookService: BookService

    @BeforeEach
    fun setUp() {
        bookCommandPort = mockk()
        bookQueryPort = mockk()
        bookMapper = mockk()

        bookService = BookService(
            bookCommandPort,
            bookQueryPort,
            bookMapper
        )
    }

    @Test
    fun `registerBook 책을 등록한다`() {
        // Given
        val command = RegisterBookCommand(
            id = "10000001",
            title = "Clean Architecture",
            author = "Robert C. Martin",
            publishedDate = LocalDate.of(2017, 9, 20)
        )
        val book = Book(
            id = command.id,
            title = command.title,
            author = command.author,
            publishedDate = command.publishedDate
        )

        every { bookMapper.toBook(command) } returns book
        every { bookCommandPort.saveBook(book) } just runs

        // When
        bookService.registerBook(command)

        // Then
        verify(exactly = 1) { bookMapper.toBook(command) }
        verify(exactly = 1) { bookCommandPort.saveBook(book) }
    }

    @Test
    fun `modifyBook 책 정보를 수정한다`() {
        // Given
        val command = ModifyBookCommand(
            id = "10000001",
            title = "Clean Code",
            author = "Robert C. Martin"
        )
        val updatedBook = Book(
            id = command.id,
            title = command.title,
            author = command.author,
            publishedDate = LocalDate.of(2008, 8, 1)
        )

        every { bookMapper.toBook(command) } returns updatedBook
        every { bookCommandPort.saveBook(updatedBook) } just runs

        // When
        bookService.modifyBook(command)

        // Then
        verify(exactly = 1) { bookMapper.toBook(command) }
        verify(exactly = 1) { bookCommandPort.saveBook(updatedBook) }
    }

    @Test
    fun `removeBook 책을 삭제한다`() {
        // Given
        val bookId = "10000001"
        every { bookCommandPort.deleteBook(bookId) } just runs

        // When
        bookService.removeBook(bookId)

        // Then
        verify(exactly = 1) { bookCommandPort.deleteBook(bookId) }
    }

    @Test
    fun `getBook ID로 책을 조회한다`() {
        // Given
        val bookId = "10000001"
        val expectedBook = Book(
            id = bookId,
            title = "Effective Kotlin",
            author = "Marcin Moskala",
            publishedDate = LocalDate.of(2020, 11, 1)
        )

        every { bookQueryPort.findBook(bookId) } returns expectedBook

        // When
        val result = bookService.getBook(bookId)

        // Then
        assertThat(result).isEqualTo(expectedBook)
        verify(exactly = 1) { bookQueryPort.findBook(bookId) }
    }
}