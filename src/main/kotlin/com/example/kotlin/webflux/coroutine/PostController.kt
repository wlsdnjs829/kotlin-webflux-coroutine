package com.example.kotlin.webflux.coroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.delayFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/posts")
@Transactional(readOnly = true)
class PostController(
        private val postRepository: PostRepository
) {
    @GetMapping
    @LogExecutionTime
    suspend fun findAll(): Flow<Post> =
            postRepository.findAll()
                    .asFlow()
                    .onCompletion { delay(10000) }
                    .also { println(Thread.currentThread()) }

    @PostMapping
    @Transactional
    suspend fun save(@RequestBody post: Post) =
            postRepository.save(post).awaitSingle() ?: IllegalArgumentException()
}