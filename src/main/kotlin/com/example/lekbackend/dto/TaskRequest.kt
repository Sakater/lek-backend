package com.example.lekbackend.dto

import com.example.lekbackend.enums.Subject
import com.example.lekbackend.enums.TaskType

data class TaskRequest (
    val text:List<String>? = null,
    val id: List<Long>? = null,
    val question: List<String>? = null,
    val subject: List<Subject>? = null,
    val taskType: List<TaskType>? = null,
    val grade: List<Int>? = null,
    val level: List<Int>? = null,
    val hint: List<String>? = null,
    val createdBy: List<String>? = null,
    val topic: List<String>? = null
)