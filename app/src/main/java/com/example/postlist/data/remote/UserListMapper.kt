package com.example.postlist.data.remote

import com.example.postlist.common.EntityMapper
import com.example.postlist.data.remote.dto.User
import com.example.postlist.domain.model.UsersUIState
import javax.inject.Inject

class UserListMapper
@Inject constructor() : EntityMapper<List<User>, List<UsersUIState>> {
    override fun mapFromEntity(entityModel: List<User>): List<UsersUIState> {
        val users = mutableListOf<UsersUIState>()
        entityModel.forEach { user ->
            users.add(
                UsersUIState(
                    imageUrl = user.url.orEmpty(),
                    name = user.name.orEmpty(),
                    postCount = String(),
                    userId = user.userId ?: 0
                )
            )
        }
        return users
    }
}
