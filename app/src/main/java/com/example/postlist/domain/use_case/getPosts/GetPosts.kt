package com.example.postlist.domain.use_case.getPosts

import com.example.postlist.common.Resource
import com.example.postlist.data.remote.PostListMapper
import com.example.postlist.domain.model.PostItem
import com.example.postlist.domain.model.PostsUIState
import com.example.postlist.domain.repository.PostListRepository
import com.example.postlist.domain.use_case.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPosts
@Inject constructor(
    private val postListRepository: PostListRepository,
    private val mapper: PostListMapper
) : BaseUseCase() {
    operator fun invoke(): Flow<Resource<List<PostsUIState>>> =
        handleData {
            mapper.mapFromEntity(
                postListRepository.getPostList()
            )
        }
}