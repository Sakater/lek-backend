package com.example.lekbackend.dto

import java.time.LocalDateTime

data class OptionResponse(
    val id: Long,
    val optionText: String,
    val position: Int? = null,
    var updatedAt: LocalDateTime? = null,
    val createdAt: LocalDateTime? = null,
)
