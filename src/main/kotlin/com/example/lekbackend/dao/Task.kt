package com.example.lekbackend.dao

import com.example.lekbackend.enums.Subject
import com.example.lekbackend.enums.TaskType
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(
    name = "tasks",
    indexes = [
        Index(name = "idx_subject_grade", columnList = "subject, grade"),
        Index(name = "idx_approved", columnList = "approved"),
        Index(name = "idx_created_by", columnList = "created_by"),
        Index(name = "idx_type", columnList = "type")
    ]
)
@EntityListeners(AuditingEntityListener::class)
class Task(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    var id: Long? = null,

    @OneToMany(mappedBy = "task", cascade = [CascadeType.ALL], orphanRemoval = true)
    var fileTasks: MutableSet<FileTask> = mutableSetOf(),

    @Column(nullable = false, columnDefinition = "TEXT")
    var question: String,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var type: TaskType,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var subject: Subject,

    @Column(nullable = false)
    var topic: String,

    @Column(nullable = true, columnDefinition = "TEXT")
    var hint: String? = null,

    @Column(nullable = false)
    var grade: Int,

    @Column(nullable = false)
    var level: Int,

    @Column(nullable = true)
    var points: Int? = null,

    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var options: MutableList<Option> = mutableListOf(),

    @Column(name = "options_in_a_row", nullable = true)
    var optionsInARow: Int? = null,

    @Column(name = "helping_lines", nullable = true, columnDefinition = "TEXT")
    var helpingLines: String? = null,

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: LocalDateTime? = null,

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime? = null,

    @Column(nullable = true)
    var createdBy: String? = null,

    @Column(nullable = false)
    var approved: Boolean = false

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Task) return false
        return id != null && id == other.id
    }

    override fun hashCode(): Int = id?.hashCode() ?: 0

    override fun toString(): String {
        return "Task(id=$id, question='$question', type=$type, subject=$subject, topic='$topic')"
    }
}
