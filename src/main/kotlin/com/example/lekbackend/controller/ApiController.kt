package com.example.lekbackend.controller

import com.example.lekbackend.dto.RequestDto
import com.example.lekbackend.dto.TaskRequest
import com.example.lekbackend.dto.TaskResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ApiController {


    @PostMapping("/search/tasks")
    fun echo(@RequestBody request: TaskRequest): TaskResponse {

        return TaskResponse()
    }

    @PostMapping("/search/files")
    fun echo(@RequestBody request: RequestDto): RequestDto {
        // Einfaches Echo-Beispiel: Request wird unverändert zurückgegeben
        return request
    }
}