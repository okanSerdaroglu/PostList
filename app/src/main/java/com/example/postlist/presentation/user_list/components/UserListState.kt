package com.example.postlist.presentation.user_list.components

import androidx.compose.runtime.mutableStateListOf
import com.example.postlist.domain.model.UsersUIState

data class UserListState(
    val isLoading: Boolean = false,
    var users: List<UsersUIState> = mutableStateListOf(),
    val error: String? = null,
    val errorId: Int? = null
)
