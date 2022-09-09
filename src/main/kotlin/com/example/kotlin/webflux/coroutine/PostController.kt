package com.example.kotlin.webflux.coroutine

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@Transactional
@RequestMapping("/posts")
class PostController(
        private val postRepository: PostRepository
) {
    @GetMapping
    suspend fun findAll(): Flow<Post> = postRepository.findAll().asFlow()

//    @GetMapping("{id}")
//    suspend fun findOne(@PathVariable id: Long): Post? =
//            postRepository.findById(id) ?: throw IllegalArgumentException()

    @PostMapping
    fun save(@RequestBody post: Post) =
            postRepository.save(post)
}