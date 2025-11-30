package com.example.lekbackend.repository

import com.example.lekbackend.dao.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository : JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {

   // @EntityGraph(attributePaths = ["options"])
   // fun findAll(spec: Specification<Task>?): MutableList<Task>
}