package com.example.lekbackend.controller

import com.example.lekbackend.dto.AddFileRequest
import com.example.lekbackend.dto.AddTaskRequest
import com.example.lekbackend.dto.AddFileResponse
import com.example.lekbackend.dto.FileRequest
import com.example.lekbackend.dto.TaskRequest
import com.example.lekbackend.dto.TaskResponse
import com.example.lekbackend.mapper.TaskMapper
import com.example.lekbackend.services.FileService
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
class ApiController(private val taskService: TaskService, private val fileService: FileService,
                    private val taskMapper: TaskMapper
) {


    @PostMapping("/search/tasks")
    fun searchTask(
        @RequestBody request: TaskRequest,
        @PageableDefault(size = 20, sort = ["createdAt"]) pageable: Pageable
    ): Page<TaskResponse> {
        return taskService.searchTasks(request, pageable)
    }

    @PostMapping("/save/task")
    fun saveTask(@RequestBody request: AddTaskRequest): TaskResponse {
        return taskMapper.toResponse(taskService.saveTask(request))
    }

    @PostMapping("/save/tasks")
    fun saveTasks(@RequestBody requests: List<AddTaskRequest>): List<TaskResponse> {
        return requests.map { request -> taskMapper.toResponse(taskService.saveTask(request)) }
    }


    @PostMapping("/search/files")
    fun searchFiles(
        @RequestBody request: FileRequest,
        @PageableDefault(size = 20, sort = ["createdAt"]) pageable: Pageable
    ): Page<FileResponse> {
        return fileService.searchFiles(request, pageable)
    }

    @PostMapping("/save/file")
    fun saveFile(@RequestBody request: AddFileRequest): AddFileResponse {
        return fileService.saveFile(request)
    }

}