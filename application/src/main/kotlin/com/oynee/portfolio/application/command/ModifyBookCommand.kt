package com.oynee.portfolio.application.command

data class ModifyBookCommand(
    val id: String,
    val title: String,
    val author: String
)