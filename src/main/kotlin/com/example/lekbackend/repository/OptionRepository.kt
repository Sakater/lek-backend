package com.example.lekbackend.repository

import com.example.lekbackend.dao.Option
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OptionRepository : JpaRepository<Option, Long> {
}
