package com.example.lekbackend.services

import com.example.lekbackend.dao.Option
import com.example.lekbackend.dao.Task
import com.example.lekbackend.dto.AddTaskRequest
import com.example.lekbackend.dto.OptionDto
import com.example.lekbackend.dto.OptionResponse
import com.example.lekbackend.dto.TaskRequest
import com.example.lekbackend.dto.TaskResponse
import com.example.lekbackend.mapper.TaskMapper
import com.example.lekbackend.repository.OptionRepository
import com.example.lekbackend.repository.TaskRepository
import com.example.lekbackend.specification.TaskSpecification
import org.springframework.stereotype.Service
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

@Service
class TaskService(
    private val taskRepository: TaskRepository, private val optionRepository: OptionRepository,
    private val taskMapper: TaskMapper
) {
    /*fun searchTasks(request: TaskRequest): List<TaskResponse> {
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
        }*/
    fun searchTasks(request: TaskRequest, pageable: Pageable): Page<TaskResponse> {
        val searchText = listOfNotNull(
            request.text?.joinToString(" "),
            request.question?.joinToString(" "),
            request.topic?.joinToString(" "),
            request.hint?.joinToString(" ")
        ).joinToString(" ").trim()

        val tasks = if (searchText.isNotEmpty()) {
            taskRepository.advancedSearch(
                searchText = searchText,
                subject = request.subject?.firstOrNull()?.name,
                grade = request.grade?.firstOrNull(),
                pageable = pageable
            )
        } else {
            taskRepository.findAll(TaskSpecification.fromRequest(request), pageable)
        }

        return tasks.map { taskMapper.toResponse(it) }
    }


    fun saveTask(request: AddTaskRequest): Task {
        val task = if (request.id != null) {
            // Update existierender Task
            taskRepository.findById(request.id).orElse(Task(
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
            ))
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

        return task
    }

}