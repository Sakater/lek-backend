package com.example.lekbackend.dao

import com.example.lekbackend.enums.Subjects
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
@Table(name = "files")
@EntityListeners(AuditingEntityListener::class)
data class Files(
    @Id
    @SequenceGenerator(name = "files_sequence", sequenceName = "files_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "files_sequence")
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    val id: Long? = null,

    @Column(nullable = false)
    var author: String,

    @Column(nullable = false)
    var title: String,

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    var subject: Subjects? = null,

    @Column(nullable = true)
    var grade: Int? = null,


    @Column(nullable = true)
    var level: Int? = null,

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: LocalDateTime? = null,

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime? = null
)