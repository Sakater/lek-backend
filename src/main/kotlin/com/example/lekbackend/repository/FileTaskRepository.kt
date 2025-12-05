package com.example.lekbackend.repository

import com.example.lekbackend.dao.FileTask
import com.example.lekbackend.dao.File
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FileTaskRepository : JpaRepository<FileTask, Long>{
    fun findAllByFileOrderByPosition(file: File): List<FileTask>
}