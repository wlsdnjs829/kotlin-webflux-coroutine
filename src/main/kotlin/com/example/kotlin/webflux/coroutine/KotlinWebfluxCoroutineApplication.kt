package com.example.kotlin.webflux.coroutine

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.web.reactive.config.EnableWebFlux

@EnableWebFlux
@SpringBootApplication
class KotlinWebfluxCoroutineApplication

fun main(args: Array<String>) {
    runApplication<KotlinWebfluxCoroutineApplication>(*args)
}
