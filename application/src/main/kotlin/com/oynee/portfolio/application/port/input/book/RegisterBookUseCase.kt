package com.oynee.portfolio.application.port.input.book

import com.oynee.portfolio.application.command.RegisterBookCommand

interface RegisterBookUseCase {

    fun registerBook(registerBookCommand: RegisterBookCommand)

}