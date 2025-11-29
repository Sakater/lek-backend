package com.example.lekbackend.dto

import java.time.LocalDateTime

data class OptionDto(
    val id: Long,
    val optionText: String,
    val position: Int,
    var updatedAt: LocalDateTime? = null,
    val createdAt: LocalDateTime? = null,
)