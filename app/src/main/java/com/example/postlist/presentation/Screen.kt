package com.example.postlist.presentation

sealed class Screen(val route: String) {
    object UserListScreen : Screen(route = "user_list")
    object PostListScreen : Screen(route = "post_list")
}
