package com.example.postlist.presentation.user_list.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postlist.common.Resource
import com.example.postlist.domain.model.PostItem
import com.example.postlist.domain.model.PostsUIState
import com.example.postlist.domain.model.UsersUIState
import com.example.postlist.domain.use_case.getPosts.GetPosts
import com.example.postlist.domain.use_case.getUsers.GetUsers
import com.example.postlist.presentation.user_list.components.UserListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserListViewModel
@Inject constructor(
    private val getUsers: GetUsers,
    private val getPosts: GetPosts
) : ViewModel() {

    private val _state = mutableStateOf(UserListState())
    val state: State<UserListState> = _state

    private var users: List<UsersUIState> = emptyList()

    private var posts: List<PostsUIState> = emptyList()

    private val postItemList = mutableListOf<PostItem>()

    init {
        getUserList()
    }

    private fun getUserList() {
        getUsers().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    setUserListSuccess(result)
                    getPostList()
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

    private fun getPostList() {
        getPosts().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    posts = result.data ?: emptyList()
                    updateUsers()
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
        _state.value = UserListState(
            isLoading = true
        )
    }

    private fun setError(message: String?) {
        _state.value = UserListState(
            error = message
        )
    }

    private fun setUserListSuccess(result: Resource<List<UsersUIState>>) {
        users = result.data ?: emptyList()
        _state.value = UserListState(
            users = users
        )
    }

    private fun getPostList(userId: Int): List<PostsUIState> {
        return posts.filter { it.userId == userId }
    }

    private fun getUrl(userId: Int): String {
        var imageUrl = String()
        users.forEach {
            if (it.userId == userId) {
                imageUrl = it.imageUrl
            }
        }
        return imageUrl
    }

    private fun updateUsers() {
        users.forEach {
            it.postCount = getPostList(it.userId).size.toString()
        }
        _state.value = UserListState(
            users = users
        )
    }

    private fun preparePostItemList(userId: Int): List<PostItem> {
        postItemList.add(
            PostItem.Image(
                getUrl(userId = userId)
            )
        )
        posts.map {
            postItemList.add(
                PostItem.PostDetail(
                    title = it.title,
                    body = it.body,
                    userId = it.userId
                )
            )
        }
        return postItemList
    }
}

