package com.example.postlist.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.postlist.R
import com.example.postlist.presentation.post_list.PostListScreen
import com.example.postlist.presentation.theme.PostListTheme
import com.example.postlist.presentation.user_list.components.UserListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PostListTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.UserListScreen.route
                    ) {
                        composable(
                            route = Screen.UserListScreen.route
                        ) {
                            UserListScreen(navController)
                        }
                        composable(
                            route = Screen.PostListScreen.route + "/{imageUrl}" + "/{userId}",
                            arguments = listOf(
                                navArgument(getString(R.string.user_image_url)) {
                                    type = NavType.StringType
                                },
                                navArgument(getString(R.string.user_id)) {
                                    type = NavType.StringType
                                },
                            )
                        ) {
                            val imageUrl = remember {
                                val imageUrl =
                                    it.arguments?.getString(getString(R.string.user_image_url))
                                imageUrl ?: String()
                            }
                            val userId = remember {
                                val userId =
                                    it.arguments?.getString(getString(R.string.user_id))
                                userId ?: String()
                            }
                            PostListScreen(
                                userId = userId,
                                imageUrl = imageUrl
                            )
                        }
                    }
                }
            }
        }
    }
}

