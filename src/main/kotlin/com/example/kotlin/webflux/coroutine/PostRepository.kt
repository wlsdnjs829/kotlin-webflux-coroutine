package com.example.kotlin.webflux.coroutine

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : ReactiveCrudRepository<Post, Long> {
}