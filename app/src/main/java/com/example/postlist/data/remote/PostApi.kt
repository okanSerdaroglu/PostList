package com.example.postlist.data.remote

import com.example.postlist.common.Constants.urlParameters
import com.example.postlist.data.remote.dto.Post
import com.example.postlist.data.remote.dto.User
import retrofit2.http.GET

interface PostApi {
    @GET("$urlParameters/posts")
    suspend fun getPostList():List<Post>

    @GET("$urlParameters/users")
    suspend fun getUserList():List<User>
}