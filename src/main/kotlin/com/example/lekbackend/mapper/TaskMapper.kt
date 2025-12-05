package com.example.lekbackend.mapper

import com.example.lekbackend.dao.Option
import com.example.lekbackend.dao.Task
import com.example.lekbackend.dto.OptionResponse
import com.example.lekbackend.dto.TaskResponse
import org.springframework.stereotype.Component

@Component
class TaskMapper {

    fun toResponse(task: Task): TaskResponse {
        return TaskResponse(
            id = task.id!!,
            question = task.question,
            type = task.type,
            subject = task.subject,
            topic = task.topic,
            hint = task.hint,
            grade = task.grade,
            level = task.level,
            points = task.points,
            options = task.options.sortedBy { it.position }.map { toOptionResponse(it) },
            optionsInARow = task.optionsInARow,
            helpingLines = task.helpingLines,
            createdAt = task.createdAt,
            updatedAt = task.updatedAt,
            createdBy = task.createdBy
        )
    }

    private fun toOptionResponse(option: Option): OptionResponse {
        return OptionResponse(
            id = option.id!!,
            optionText = option.optionText
        )
    }
}
