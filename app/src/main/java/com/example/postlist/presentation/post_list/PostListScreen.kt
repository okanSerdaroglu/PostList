package com.example.postlist.presentation.post_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.postlist.presentation.post_list.components.PostImageContent
import com.example.postlist.presentation.post_list.components.PostTextContent
import com.example.postlist.presentation.post_list.view_model.PostListViewModel


@Composable
fun PostListScreen(
    imageUrl: String,
    userId: Int,
    viewModel: PostListViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val state = viewModel.state.value

    val url = viewModel.url.value

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                PostImageContent(imageUrl = url)
            }
            items(state.posts) {
                PostTextContent(title = it.title, body = it.body)
            }
        }

        (state.error ?: state.errorId?.let { context.getString(it) })?.let {
            Text(
                text = it,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

}