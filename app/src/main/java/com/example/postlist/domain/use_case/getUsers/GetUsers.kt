package com.example.postlist.domain.use_case.getUsers

import com.example.postlist.common.Resource
import com.example.postlist.data.remote.UserListMapper
import com.example.postlist.domain.model.UsersUIState
import com.example.postlist.domain.repository.PostListRepository
import com.example.postlist.domain.use_case.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsers
@Inject constructor(
    private val postListRepository: PostListRepository,
    private val mapper: UserListMapper
) : BaseUseCase() {
    operator fun invoke(): Flow<Resource<List<UsersUIState>>> =
        handleData {
            mapper.mapFromEntity(
                postListRepository.getUserList()
            )
        }
}