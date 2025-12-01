package com.example.lekbackend.controller

import com.example.lekbackend.dao.Task
import com.example.lekbackend.dto.AddTaskRequest
import com.example.lekbackend.dto.TaskRequest
import com.example.lekbackend.dto.TaskResponse
import com.example.lekbackend.services.TaskService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ApiController(private val taskService: TaskService) {


    @PostMapping("/search/tasks")
    fun searchTask(
        @RequestBody request: TaskRequest,
        @PageableDefault(size = 20, sort = ["createdAt"]) pageable: Pageable
    ): Page<Task> {
        return taskService.searchTasks(request, pageable)
    }

    @PostMapping("/save/task")
    fun saveTask(@RequestBody request: AddTaskRequest): TaskResponse {
        return taskService.saveTask(request)
    }


    /*@PostMapping("/search/files")
    fun echo(@RequestBody request: RequestDto): RequestDto {
        // Einfaches Echo-Beispiel: Request wird unverändert zurückgegeben
        return request
    }*/
}