package com.example.lekbackend.repository

import com.example.lekbackend.dao.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable


interface TaskRepository : JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {

    @Query(
        value = """
        SELECT t.* FROM tasks t
        WHERE t.search_vector @@ plainto_tsquery('german', :searchText)
          AND t.approved = true
        ORDER BY ts_rank(t.search_vector, plainto_tsquery('german', :searchText)) DESC
    """, nativeQuery = true
    )
    fun fullTextSearch(
        searchText: String,
        pageable: Pageable
    ): Page<Task>

    @Query(
        value = """
        SELECT t.* FROM tasks t
        WHERE (:searchText IS NULL OR t.search_vector @@ plainto_tsquery('german', :searchText))
          AND (:subject IS NULL OR t.subject = CAST(:subject AS text))
          AND (:grade IS NULL OR t.grade = :grade)
          AND t.approved = true
        ORDER BY
            CASE WHEN :searchText IS NOT NULL
            THEN ts_rank(t.search_vector, plainto_tsquery('german', :searchText))
            ELSE 0 END DESC,
            t.created_at DESC
    """,
        countQuery = """
        SELECT COUNT(*) FROM tasks t
        WHERE (:searchText IS NULL OR t.search_vector @@ plainto_tsquery('german', :searchText))
          AND (:subject IS NULL OR t.subject = CAST(:subject AS text))
          AND (:grade IS NULL OR t.grade = :grade)
          AND t.approved = true
    """,
        nativeQuery = true
    )
    fun advancedSearch(
        searchText: String?,
        subject: String?,
        grade: Int?,
        pageable: Pageable
    ): Page<Task>

    @Query(value = """
    SELECT 
        t.*,
        ts_headline('german', t.question, plainto_tsquery('german', :searchText)) as highlighted_question
    FROM tasks t
    WHERE t.search_vector @@ plainto_tsquery('german', :searchText)
""", nativeQuery = true)
    fun searchWithHighlights(searchText: String): List<Map<String, Any>>
}
