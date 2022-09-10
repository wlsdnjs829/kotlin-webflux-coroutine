package com.example.kotlin.webflux.coroutine

import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : R2dbcRepository<Post, Long> {
}