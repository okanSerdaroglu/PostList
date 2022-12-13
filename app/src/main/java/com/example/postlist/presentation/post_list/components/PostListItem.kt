package com.example.postlist.presentation.post_list.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun ImageContent(imageUrl: String) {
    Card(
        Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(4.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(20.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .build(),
            contentDescription = String(),
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1F)
                .clip(RectangleShape),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun TextContent(
    title: String,
    body: String
) {
    Card(
        Modifier
            .fillMaxWidth()
            .height(130.dp)
            .padding(8.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier.padding(start = 16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = body,
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            )
        }
    }
}

@Preview
@Composable
fun TextContentPreview(){
    TextContent(
        title = "dolorem eum magni eos aperiam quia",
        body = "ut aspernatur corporis harum nihil quis provident sequi\nmollitia nobis aliquid molestiae\nperspiciatis et ea nemo"
    )
}

@Preview
@Composable
fun ImageContentPreview(){
    ImageContent(
        imageUrl = "https://dummyimage.com/600/92c952&text=User+1"
    )
}