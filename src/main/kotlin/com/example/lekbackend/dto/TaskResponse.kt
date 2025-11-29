package com.example.lekbackend.dto

import com.example.lekbackend.enums.Subject
import com.example.lekbackend.enums.TaskType
import java.time.LocalDateTime

data class TaskResponse(
    val id: Long,
    val question: String,
    val type: TaskType,
    val subject: Subject,
    val topic: String,
    val hint: String,
    val grade: Int,
    val level: Int,
    val points: Int,
    val options: List<OptionDto> = emptyList(),
    val optionsInARow: Int? = null,
    val helpingLines: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null,
    val createdBy: String? = null
)