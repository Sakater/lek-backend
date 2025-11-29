package com.example.lekbackend.dao

import com.example.lekbackend.enums.Subjects
import com.example.lekbackend.enums.TaskType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(name = "tasks")
@EntityListeners(AuditingEntityListener::class)
data class Tasks(
    @Id
    @SequenceGenerator(name = "tasks_sequence", sequenceName = "tasks_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasks_sequence")
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    val id: Long? = null,

    @Column(nullable = false)
    var question: String,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var type: TaskType,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var subject: Subjects,

    @Column(nullable = false)
    var grade: Int,

    @Column(nullable = false)
    var hint: String,

    @Column(nullable = false)
    var level: Int,

    @Column(nullable = false)
    var points: Int,

    @Column(name = "options_in_a_row", nullable = true)
    var optionsInARow: Int? = null,

    @Column(name = "helping_lines", nullable = true)
    var helpingLines: String? = null,

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: LocalDateTime? = null,

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime? = null
)