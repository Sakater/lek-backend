package com.example.lekbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class LekBackendApplication

fun main(args: Array<String>) {
    runApplication<LekBackendApplication>(*args)
}
