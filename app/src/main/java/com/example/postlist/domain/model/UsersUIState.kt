package com.example.postlist.domain.model

data class UsersUIState(
    val imageUrl: String,
    val name: String,
    var postCount: String,
    val userId: Int
)
