package com.oynee.portfolio.adapter.api.mapper

import com.oynee.portfolio.adapter.api.request.RegisterBookRequest
import com.oynee.portfolio.adapter.api.request.ModifyBookRequest
import com.oynee.portfolio.adapter.api.response.BookResponse
import com.oynee.portfolio.application.command.ModifyBookCommand
import com.oynee.portfolio.application.command.RegisterBookCommand
import com.oynee.portfolio.domain.Book
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface BookApiMapper {

    fun toCommand(registerBookRequest: RegisterBookRequest): RegisterBookCommand

    fun toCommand(modifyBookRequest: ModifyBookRequest): ModifyBookCommand

    fun toResponse(book: Book): BookResponse

}