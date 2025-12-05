package com.example.lekbackend.dto

import com.example.lekbackend.enums.Subject
import java.util.Collections.emptySet

data class FileRequest(
    val id: Long?=null,
    val title: String?=null,
    val subject: MutableSet<Subject>?=emptySet(),
    val topic: MutableSet<String>?=emptySet(),
    val grade: Int? = null,
    val level: Int? = null,
    val createdBy: String? = null,
    val tasks: List<AddTaskRequest>? = emptyList()
)