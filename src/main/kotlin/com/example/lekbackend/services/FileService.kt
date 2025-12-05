package com.example.lekbackend.services

import com.example.lekbackend.dao.File
import com.example.lekbackend.dao.FileTask
import com.example.lekbackend.dto.AddFileRequest
import com.example.lekbackend.dto.AddFileResponse
import com.example.lekbackend.dto.FileRequest
import com.example.lekbackend.repository.FileRepository
import com.example.lekbackend.repository.FileTaskRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FileService(
    private val fileRepository: FileRepository,
    private val taskService: TaskService,
    private val fileTaskRepository: FileTaskRepository
) {

    fun searchFiles(request: FileRequest, pageable: Pageable): List<AddFileResponse> {
        // Implement search logic here, possibly using specifications or custom queries
        val files = fileRepository.findAll(pageable).content

        return files.map { file ->
            // lade FileTask-Einträge und mappe auf TaskResponse über TaskService
            val tasks = fileTaskRepository
                .findAllByFileOrderByPosition(file)
                .map { ft -> taskService.toTaskResponse(ft.task) }

            AddFileResponse(
                id = file.id,
                title = file.title,
                subject = file.subject,
                topic = file.topic,
                grade = file.grade,
                level = file.level,
                createdAt = file.createdAt,
                updatedAt = file.updatedAt,
                createdBy = file.createdBy,
                tasks = tasks
            )
        }
    }

    @Transactional
    fun saveFile(request: AddFileRequest): AddFileResponse {
        val file = File(
            title = request.title,
            subject = request.subject,
            topic = request.topic,
            grade = request.grade,
            level = request.level,
            createdBy = request.createdBy
        )

        val savedFile = fileRepository.save(file)

        request.tasks?.forEachIndexed { index, taskRequest ->
            val task = taskService.saveTask(taskRequest)
            val fileTask = FileTask(
                file = savedFile,
                task = task,
                position = index,
            )
            fileTaskRepository.save(fileTask)
        }

        return AddFileResponse(
            id = savedFile.id,
            title = savedFile.title,
            subject = savedFile.subject,
            topic = savedFile.topic,
            grade = savedFile.grade,
            level = savedFile.level,
            createdAt = savedFile.createdAt,
            updatedAt = savedFile.updatedAt,
            createdBy = savedFile.createdBy
        )
    }

}
