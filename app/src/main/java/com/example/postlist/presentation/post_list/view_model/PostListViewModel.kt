package com.example.postlist.presentation.post_list.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postlist.common.Resource
import com.example.postlist.domain.model.PostsUIState
import com.example.postlist.domain.use_case.getPosts.GetPosts
import com.example.postlist.presentation.post_list.components.PostListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PostListViewModel
@Inject constructor(
    private val getPosts: GetPosts,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(PostListState())
    val state: State<PostListState> = _state

    private var posts: List<PostsUIState> = emptyList()

    var imageUrl = mutableStateOf(String())
    val userId = mutableStateOf(0)

    init {
        savedStateHandle.get<String>("imageUrl")?.let {
            imageUrl.value = it
        }
        savedStateHandle.get<Int>("userId")?.let {
            userId.value = it
        }
        getPostList()
    }

    private fun getPostList() {
        getPosts().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    setPostListSuccess(result)
                }
                is Resource.Error -> {
                    setError(result.message)
                }
                is Resource.Loading -> {
                    setLoading()
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun setLoading() {
        _state.value = PostListState(
            isLoading = true
        )
    }

    private fun setError(message: String?) {
        _state.value = PostListState(
            error = message
        )
    }

    private fun setPostListSuccess(result: Resource<List<PostsUIState>>) {
        posts = result.data ?: emptyList()
        _state.value = PostListState(
            posts = getPostList(userId = userId.value)
        )
    }

    private fun getPostList(userId: Int): List<PostsUIState> {
        return posts.filter { it.userId == userId }
    }


}