package com.example.postlist.presentation.user_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.postlist.domain.model.UsersUIState

@Composable
fun UserListItem(
    userItemUIState: UsersUIState,
    onItemClick: (userId:Int) -> Unit
) {
    Card(
        Modifier
            .fillMaxWidth()
            .height(130.dp)
            .padding(8.dp).clickable {
               onItemClick(userItemUIState.userId)
            },
        elevation = 4.dp,
        shape = RoundedCornerShape(20.dp)
    ) {
        UserListItemContent(
            userItemUIState = userItemUIState
        )
    }
}

@Composable
fun UserListItemContent(userItemUIState: UsersUIState) {
    Row(
        modifier = Modifier.padding(start = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ImageContent(
            imageUrl = userItemUIState.imageUrl
        )
        TextContent(
            name = userItemUIState.name,
            postCount = userItemUIState.postCount
        )
    }
}

@Composable
fun ImageContent(imageUrl: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .build(),
        contentDescription = String(),
        modifier = Modifier
            .height(90.dp)
            .width(90.dp)
            .aspectRatio(1F)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun TextContent(
    name: String,
    postCount: String
) {
    Column(
        modifier = Modifier.padding(start = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = name,
            style = TextStyle(
                color = Color.Gray,
                fontSize = 16.sp
            ),
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = postCount,
            style = TextStyle(
                color = Color.Gray,
                fontSize = 16.sp
            ),
            maxLines = 1
        )
    }
}

@Preview
@Composable
fun UserListItemPreview() {
    UserListItem(
        UsersUIState(
            imageUrl = "https://dummyimage.com/600/92c952&text=User+1",
            name = "User 1",
            postCount = "5",
            userId = 1
        )
    ) {}
}