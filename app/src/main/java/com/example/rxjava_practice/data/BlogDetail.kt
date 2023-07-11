package com.example.rxjava_practice.data

data class BlogDetail(
    val id: Long,
    val userId: Long,
    val title: String,
    val content: String,
    val user: User
)
