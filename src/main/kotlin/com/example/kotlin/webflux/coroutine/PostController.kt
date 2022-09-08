package com.example.kotlin.webflux.coroutine

import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/posts")
class PostController(
        private val postRepository: PostRepository
) {
    @GetMapping
    suspend fun findAll(): Flux<Post> = postRepository.findAll()

    @GetMapping("{id}")
    suspend fun findOne(@PathVariable id: Long): Post? =
            postRepository.findById(id) ?: throw IllegalArgumentException()

    @PostMapping
    suspend fun save(@RequestBody post: Post) = postRepository.save(post)
}