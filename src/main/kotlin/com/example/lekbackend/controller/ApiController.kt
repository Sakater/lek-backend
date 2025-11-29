package com.example.lekbackend.controller

import com.example.lekbackend.dto.TaskRequest
import com.example.lekbackend.dto.TaskResponse
import com.example.lekbackend.services.TaskService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ApiController (private val taskService: TaskService){


    @PostMapping("/search/tasks")
    fun echo(@RequestBody request: TaskRequest): List<TaskResponse> {
        return taskService.searchTasks(request)
    }

    /*@PostMapping("/search/files")
    fun echo(@RequestBody request: RequestDto): RequestDto {
        // Einfaches Echo-Beispiel: Request wird unverändert zurückgegeben
        return request
    }*/
}