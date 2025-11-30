package com.example.lekbackend.dto

import java.time.LocalDateTime

data class AddOptionRequest(

    val id: Long?=null,
    val optionText: String,
    val position: Int?=null,
    var updatedAt: LocalDateTime? = null,
    val createdAt: LocalDateTime? = null,
)
