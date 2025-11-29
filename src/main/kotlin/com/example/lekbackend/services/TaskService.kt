package com.example.lekbackend.services

import com.example.lekbackend.dto.OptionDto
import com.example.lekbackend.dto.TaskRequest
import com.example.lekbackend.dto.TaskResponse
import com.example.lekbackend.repository.TaskRepository
import com.example.lekbackend.specification.TaskSpecification
import org.springframework.stereotype.Service

@Service
class TaskService(
    private val taskRepository: TaskRepository
) {
    fun searchTasks(request: TaskRequest): List<TaskResponse> {
        val spec = TaskSpecification.fromRequest(request)
        return taskRepository.findAll(spec).map { task ->
            // Mapping zu TaskResponse
            TaskResponse(
                id = task.id!!,
                question = task.question,
                type = task.type,
                subject = task.subject,
                topic = task.topic,
                hint = task.hint,
                grade = task.grade,
                level = task.level,
                points = task.points,
                options = task.options.map { option ->
                    OptionDto(
                        id = option.id!!,
                        optionText = option.optionText,
                        position = option.position
                    )
                },
                optionsInARow = task.optionsInARow,
                helpingLines = task.helpingLines,
                createdAt = task.createdAt,
                updatedAt = task.updatedAt,
                createdBy = task.createdBy
            )
        }
    }
}