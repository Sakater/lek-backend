package com.example.lekbackend.dto

import com.example.lekbackend.enums.Subject
import java.time.LocalDateTime

data class AddFileResponse(
    val id: Long?,
    val title: String,
    val subject: Set<Subject>,
    val topic: Set<String>,
    val grade: Int?,
    val level: Int?,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?,
    val createdBy: String?,
    val tasks: List<TaskResponse>?
)
