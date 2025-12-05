package com.example.lekbackend.dao

import com.example.lekbackend.enums.Subject
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(name = "files")
@EntityListeners(AuditingEntityListener::class)
data class File(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    val id: Long? = null,

    @OneToMany(mappedBy = "file", cascade = [CascadeType.ALL])
    val fileTasks: MutableSet<FileTask> = mutableSetOf(),

    @Column(nullable = false)
    val title: String,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val subject: MutableSet<Subject>,

    @Column(nullable = false)
    val topic: MutableSet<String>,

    @Column(nullable = true)
    val grade: Int? = null,

    @Column(nullable = true)
    val level: Int? = null,

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime? = null,

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    val updatedAt: LocalDateTime? = null,

    @Column(name = "created_by", nullable = true)
    val createdBy: String? = null


)