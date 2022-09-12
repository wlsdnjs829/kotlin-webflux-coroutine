package com.example.kotlin.webflux.coroutine

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table
data class Post(
    @Id val id: Long?,
    @Column val title: String,
    @Column val content: String
)