package com.oynee.portfolio.adapter.api.request

data class ModifyBookRequest(
    val id: String,
    val title: String,
    val author: String,
)