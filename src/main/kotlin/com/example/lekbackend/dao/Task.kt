package com.example.lekbackend.dao

import com.example.lekbackend.enums.Subject
import com.example.lekbackend.enums.TaskType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.CascadeType
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(name = "tasks")
@EntityListeners(AuditingEntityListener::class)
data class Task(
    @Id
    @SequenceGenerator(name = "tasks_sequence", sequenceName = "tasks_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasks_sequence")
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    val id: Long? = null,

    @OneToMany(mappedBy = "task", cascade = [CascadeType.ALL])
    val fileTasks: MutableSet<FileTask> = mutableSetOf(),

    @Column(nullable = false)
    val question: String,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val type: TaskType,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val subject: Subject,

    @Column(nullable = false)
    val topic: String,

    @Column(nullable = false)
    val hint: String,

    @Column(nullable = false)
    val grade: Int,

    @Column(nullable = false)
    val level: Int,

    @Column(nullable = false)
    val points: Int,

    @Column(name = "options_in_a_row", nullable = true)
    val optionsInARow: Int? = null,

    @Column(name = "helping_lines", nullable = true)
    val helpingLines: String? = null,

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime? = null,

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    val updatedAt: LocalDateTime? = null,

    @Column(nullable = true)
    val createdBy: String? = null,
)