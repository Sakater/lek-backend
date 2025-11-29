package com.example.lekbackend.dao

import com.example.lekbackend.enums.Subject
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(name = "files")
@EntityListeners(AuditingEntityListener::class)
data class File(
    @Id
    @SequenceGenerator(name = "files_sequence", sequenceName = "files_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "files_sequence")
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    val id: Long? = null,

    @OneToMany(mappedBy = "file", cascade = [CascadeType.ALL])
    val fileTasks: MutableSet<FileTask> = mutableSetOf(),

    @Column(nullable = false)
    val author: String,

    @Column(nullable = false)
    val title: String,

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    val subject: Subject? = null,

    @Column(nullable = false)
    val topic: String,

    @Column(nullable = true)
    val grade: Int? = null,


    @Column(nullable = true)
    val level: Int? = null,

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime? = null,

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    val updatedAt: LocalDateTime? = null
)