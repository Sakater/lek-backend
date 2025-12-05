package com.example.lekbackend.dto

import com.example.lekbackend.enums.Subject

data class AddFileRequest(
    val title: String,
    val subject: MutableSet<Subject>,
    val topic: MutableSet<String>,
    val grade: Int? = null,
    val level: Int? = null,
    val createdBy: String? = null,
    val tasks: List<AddTaskRequest>? = emptyList()
)