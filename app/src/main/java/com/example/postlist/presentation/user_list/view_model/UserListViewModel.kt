package com.example.postlist.presentation.user_list.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postlist.common.Resource
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

    init {
        getUserList()
        getPostList()
    }

    private fun getUserList() {
        getUsers().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    setUserListSuccess(result)
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

}

