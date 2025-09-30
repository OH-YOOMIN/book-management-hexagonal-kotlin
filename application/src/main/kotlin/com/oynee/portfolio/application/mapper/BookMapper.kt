package com.oynee.portfolio.application.mapper

import com.oynee.portfolio.application.command.ModifyBookCommand
import com.oynee.portfolio.application.command.RegisterBookCommand
import com.oynee.portfolio.domain.Book
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface BookMapper {

    fun toBook(registerBookCommand: RegisterBookCommand): Book

    fun toBook(modifyBookCommand: ModifyBookCommand): Book

}