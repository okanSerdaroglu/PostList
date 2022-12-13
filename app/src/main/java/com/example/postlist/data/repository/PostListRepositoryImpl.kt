package com.example.postlist.data.repository

import com.example.postlist.data.remote.PostApi
import com.example.postlist.data.remote.dto.Post
import com.example.postlist.data.remote.dto.User

import com.example.postlist.domain.repository.PostListRepository
import javax.inject.Inject

class PostListRepositoryImpl
@Inject constructor(
   private val postApi: PostApi
): PostListRepository {

    override suspend fun getPostList(): List<Post> = postApi.getPostList()

    override suspend fun getUserList(): List<User> = postApi.getUserList()

}