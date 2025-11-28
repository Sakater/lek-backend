package com.example.lekbackend.tasks

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter


@Getter
@Setter
@NoArgsConstructor
@Entity
@EqualsAndHashCode
public class Tasks {
    @Id
    @SequenceGenerator(name = "tasks_sequence", sequenceName = "tasks_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasks_sequence")
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private val id: Long? = null
    private val question: String? = null;
}