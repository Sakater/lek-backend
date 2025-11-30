package com.example.lekbackend.dto

import com.example.lekbackend.enums.Subject
import com.example.lekbackend.enums.TaskType

data class AddTaskRequest(
    val id: Long?=null,
    val question: String,
    val type: TaskType,
    val subject: Subject,
    val topic: String,
    val hint: String?=null,
    val grade: Int,
    val level: Int,
    val points: Int,
    val options: List<AddOptionRequest>? = emptyList(),
    val optionsInARow: Int? = null,
    val helpingLines: String? = null,
    val createdBy: String? = null
)

