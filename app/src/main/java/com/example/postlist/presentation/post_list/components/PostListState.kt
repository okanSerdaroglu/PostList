package com.example.postlist.presentation.post_list.components

import androidx.compose.runtime.mutableStateListOf
import com.example.postlist.domain.model.PostsUIState

data class PostListState(
    val isLoading: Boolean = false,
    var posts: List<PostsUIState> = mutableStateListOf(),
    val error: String? = null,
    val errorId: Int? = null
)