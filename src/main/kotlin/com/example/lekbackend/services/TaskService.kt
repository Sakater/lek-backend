package com.example.lekbackend.services

import com.example.lekbackend.repository.TaskRepository
import lombok.AllArgsConstructor
import org.springframework.stereotype.Service

@Service
@AllArgsConstructor
class TaskService(
    private val taskRepository: TaskRepository
) {

}