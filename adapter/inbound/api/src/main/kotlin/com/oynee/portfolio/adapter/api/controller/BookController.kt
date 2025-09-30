package com.oynee.portfolio.adapter.api.controller

import com.oynee.portfolio.adapter.api.mapper.BookApiMapper
import com.oynee.portfolio.adapter.api.request.RegisterBookRequest
import com.oynee.portfolio.adapter.api.request.ModifyBookRequest
import com.oynee.portfolio.adapter.api.response.BookResponse
import com.oynee.portfolio.application.port.input.book.GetBookUseCase
import com.oynee.portfolio.application.port.input.book.ModifyBookUseCase
import com.oynee.portfolio.application.port.input.book.RegisterBookUseCase
import com.oynee.portfolio.application.port.input.book.RemoveBookUseCase
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["/v1/books"])
class BookController (
    val registerBookUseCase: RegisterBookUseCase,
    val modifyBookUseCase: ModifyBookUseCase,
    val removeBookUseCase: RemoveBookUseCase,
    val getBookUseCase: GetBookUseCase,

    val bookApiMapper: BookApiMapper
) {

    @PostMapping
    fun saveBook(@RequestBody @Valid registerBookRequest: RegisterBookRequest): ResponseEntity<Unit> {
        bookApiMapper.toCommand(registerBookRequest)
            .let { command -> registerBookUseCase.registerBook(command) }

        return ResponseEntity.ok().build()
    }

    @PutMapping
    fun putBook(@RequestBody @Valid modifyBookRequest: ModifyBookRequest): ResponseEntity<Unit> {
        bookApiMapper.toCommand(modifyBookRequest)
            .let { command -> modifyBookUseCase.modifyBook(command) }

        return ResponseEntity.ok().build()
    }

    @DeleteMapping(path = ["/{id}"])
    fun deleteBook(@PathVariable id: String): ResponseEntity<Unit> {
        removeBookUseCase.removeBook(id)

        return ResponseEntity.ok().build()
    }

    @GetMapping(path = ["/{id}"])
    fun getBook(@PathVariable id: String): ResponseEntity<BookResponse> {
        val response = getBookUseCase.getBook(id)
            .let { book -> bookApiMapper.toResponse(book) }

        return ResponseEntity.ok(response)
    }

}