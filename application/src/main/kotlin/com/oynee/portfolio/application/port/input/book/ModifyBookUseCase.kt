package com.oynee.portfolio.application.port.input.book

import com.oynee.portfolio.application.command.ModifyBookCommand

interface ModifyBookUseCase {

    fun modifyBook(modifyBookCommand: ModifyBookCommand)

}