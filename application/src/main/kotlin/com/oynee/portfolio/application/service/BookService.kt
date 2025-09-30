package com.oynee.portfolio.application.service

import com.oynee.portfolio.application.port.input.book.GetBookUseCase
import com.oynee.portfolio.domain.Book
import com.oynee.portfolio.application.command.ModifyBookCommand
import com.oynee.portfolio.application.command.RegisterBookCommand
import com.oynee.portfolio.application.mapper.BookMapper
import com.oynee.portfolio.application.port.input.book.ModifyBookUseCase
import com.oynee.portfolio.application.port.input.book.RegisterBookUseCase
import com.oynee.portfolio.application.port.input.book.RemoveBookUseCase
import com.oynee.portfolio.application.port.output.persistence.BookCommandPort
import com.oynee.portfolio.application.port.output.persistence.BookQueryPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.IllegalStateException

@Service
@Transactional
class BookService(
    private val bookCommandPort: BookCommandPort,
    private val bookQueryPort: BookQueryPort,

    private val bookMapper: BookMapper
): RegisterBookUseCase, ModifyBookUseCase, RemoveBookUseCase, GetBookUseCase
{

    override fun registerBook(registerBookCommand: RegisterBookCommand) {
        if (bookQueryPort.existsById(registerBookCommand.id)) {
            throw IllegalStateException("이미 존재하는 Book(id=${registerBookCommand.id})은 등록할 수 없습니다.")
        }
        bookMapper.toBook(registerBookCommand)
            .let { book -> bookCommandPort.saveBook(book) }
    }

    override fun modifyBook(modifyBookCommand: ModifyBookCommand) {
        val existing = bookQueryPort.findBook(modifyBookCommand.id)
        val updated = existing.copy(
            title = modifyBookCommand.title,
            author = modifyBookCommand.author
        )
        bookCommandPort.saveBook(updated)
    }

    override fun removeBook(id: String) {
        bookCommandPort.deleteBook(id)
    }

    override fun getBook(id: String): Book {
        return bookQueryPort.findBook(id)
    }

}