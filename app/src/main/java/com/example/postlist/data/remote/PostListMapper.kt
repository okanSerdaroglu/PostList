package com.example.postlist.data.remote

import com.example.postlist.common.EntityMapper
import com.example.postlist.data.remote.dto.Post
import com.example.postlist.domain.model.PostsUIState
import javax.inject.Inject

class PostListMapper
@Inject constructor() : EntityMapper<List<Post>, List<PostsUIState>> {
    override fun mapFromEntity(entityModel: List<Post>): List<PostsUIState> {
        val posts = mutableListOf<PostsUIState>()
        entityModel.forEach { post ->
            posts.add(
                PostsUIState(
                    String(),
                    post.title.orEmpty(),
                    post.body.orEmpty()
                )
            )
        }
        return posts
    }
}