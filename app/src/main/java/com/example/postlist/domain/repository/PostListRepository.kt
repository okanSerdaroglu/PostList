package com.example.postlist.domain.repository

import com.example.postlist.data.remote.dto.Post
import com.example.postlist.data.remote.dto.User

interface PostListRepository {
    suspend fun getPostList(): List<Post>
    suspend fun getUserList(): List<User>
}