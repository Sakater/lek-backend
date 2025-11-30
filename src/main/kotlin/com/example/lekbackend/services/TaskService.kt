package com.example.lekbackend.services

import com.example.lekbackend.dao.Option
import com.example.lekbackend.dao.Task
import com.example.lekbackend.dto.AddTaskRequest
import com.example.lekbackend.dto.OptionDto
import com.example.lekbackend.dto.TaskRequest
import com.example.lekbackend.dto.TaskResponse
import com.example.lekbackend.repository.OptionRepository
import com.example.lekbackend.repository.TaskRepository
import com.example.lekbackend.specification.TaskSpecification
import org.springframework.stereotype.Service

@Service
class TaskService(
    private val taskRepository: TaskRepository, private val optionRepository: OptionRepository
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
                options = task.options.sortedBy { it.position }.map { option ->
                    OptionDto(
                        id = option.id!!, optionText = option.optionText, position = option.position
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

    fun saveTask(request: AddTaskRequest): TaskResponse {
        val task = if (request.id != null) {
            // Update existierender Task
            taskRepository.findById(request.id).orElseThrow {
                IllegalArgumentException("Task mit ID ${request.id} nicht gefunden")
            }.apply {
                this.question = request.question
                this.type = request.type
                this.subject = request.subject
                this.topic = request.topic
                this.hint = request.hint
                this.grade = request.grade
                this.level = request.level
                this.points = request.points
                this.optionsInARow = request.optionsInARow
                this.helpingLines = request.helpingLines
                this.createdBy = request.createdBy
            }
        } else {
            Task(
                question = request.question,
                type = request.type,
                subject = request.subject,
                topic = request.topic,
                hint = request.hint,
                grade = request.grade,
                level = request.level,
                points = request.points,
                optionsInARow = request.optionsInARow,
                helpingLines = request.helpingLines,
                createdBy = request.createdBy
            )
        }

        val savedTask = taskRepository.save(task)

        // Options separat speichern
        val savedOptions = request.options?.mapIndexed { index, dto ->
            val option = Option(
                task = savedTask, optionText = dto.optionText, position = dto.position ?: index
            )
            optionRepository.save(option)
        } ?: emptyList()

        return TaskResponse(
            id = savedTask.id!!,
            question = savedTask.question,
            type = savedTask.type,
            subject = savedTask.subject,
            topic = savedTask.topic,
            hint = savedTask.hint,
            grade = savedTask.grade,
            level = savedTask.level,
            points = savedTask.points,
            options = savedOptions.map { option -> OptionDto(option.id!!, option.optionText, option.position) },
            optionsInARow = savedTask.optionsInARow,
            helpingLines = savedTask.helpingLines,
            createdAt = savedTask.createdAt,
            updatedAt = savedTask.updatedAt,
            createdBy = savedTask.createdBy
        )
    }

}