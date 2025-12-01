package com.example.lekbackend.specification

import com.example.lekbackend.dao.Task
import com.example.lekbackend.dto.TaskRequest
import com.example.lekbackend.enums.Subject
import com.example.lekbackend.enums.TaskType
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
import org.springframework.data.jpa.domain.Specification

object TaskSpecification {
    fun fromRequest(request: TaskRequest): Specification<Task> {
        return Specification { root: Root<Task>, _: CriteriaQuery<*>, cb: CriteriaBuilder ->
            val predicates = mutableListOf<Predicate>()

            request.text?.takeIf { it.isNotEmpty() }?.let {
                val likePredicates = it.map { text ->
                    cb.like(cb.lower(root.get("text")), "%${text.lowercase()}%")
                }
                predicates.add(cb.or(*likePredicates.toTypedArray()))
            }

            request.id?.takeIf { it.isNotEmpty() }?.let {
                predicates.add(root.get<Long>("id").`in`(it))
            }

            request.question?.takeIf { it.isNotEmpty() }?.let {
                val likePredicates = it.map { question ->
                    cb.like(cb.lower(root.get("question")), "%${question.lowercase()}%")
                }
                predicates.add(cb.or(*likePredicates.toTypedArray()))
            }

            request.subject?.takeIf { it.isNotEmpty() }?.let {
                predicates.add(root.get<Subject>("subject").`in`(it))
            }
            request.taskType?.takeIf { it.isNotEmpty() }?.let {
                predicates.add(root.get<TaskType>("type").`in`(it))
            }

            request.grade?.takeIf { it.isNotEmpty() }?.let {
                predicates.add(root.get<Int>("grade").`in`(it))
            }

            request.level?.takeIf { it.isNotEmpty() }?.let {
                predicates.add(root.get<Int>("level").`in`(it))
            }

            request.hint?.takeIf { it.isNotEmpty() }?.let {
                val likePredicates = it.map { hint ->
                    cb.like(cb.lower(root.get("hint")), "%${hint.lowercase()}%")
                }
                predicates.add(cb.or(*likePredicates.toTypedArray()))
            }

            request.createdBy?.takeIf { it.isNotEmpty() }?.let {
                val likePredicates = it.map { createdBy ->
                    cb.like(cb.lower(root.get("createdBy")), "%${createdBy.lowercase()}%")
                }
                predicates.add(cb.or(*likePredicates.toTypedArray()))
            }

            request.topic?.takeIf { it.isNotEmpty() }?.let {
                val likePredicates = it.map { topic ->
                    cb.like(cb.lower(root.get("topic")), "%${topic.lowercase()}%")
                }
                predicates.add(cb.or(*likePredicates.toTypedArray()))
            }
            predicates.add(cb.equal(root.get<Boolean>("approved"), true))
            cb.and(*predicates.toTypedArray())
        }
    }
}