package com.example

import com.example.postlist.data.remote.dto.Post
import com.example.postlist.data.remote.dto.User

object MockData {

    private val user1 = User(
        albumId = 1,
        userId = 1,
        name = "User 1",
        url = "https://dummyimage.com/600/92c952&text=User+1",
        thumbnailUrl = "https://dummyimage.com/150/92c952&text=User+1"
    )

    private val user2 = User(
        albumId = 2,
        userId = 2,
        name = "User 2",
        url = "https://dummyimage.com/600/771796&text=User+2",
        thumbnailUrl = "https://dummyimage.com/150/771796&text=User+2"
    )

    private val user3 = User(
        albumId = 3,
        userId = 3,
        name = "User 3",
        url = "https://dummyimage.com/600/24f355&text=User+3",
        thumbnailUrl = "https://dummyimage.com/150/24f355&text=User+3"
    )

    private val post1 = Post(
        userId = 1,
        id = 1,
        title = "title1",
        body = "body1"
    )

    private val post2 = Post(
        userId = 2,
        id = 2,
        title = "title2",
        body = "body2"
    )

    private val post3 = Post(
        userId = 3,
        id = 3,
        title = "title3",
        body = "body3"
    )


    val userList: List<User> = arrayListOf<User>().apply {
        add(user1)
        add(user2)
        add(user3)
    }

    val postList : List<Post> = arrayListOf<Post>().apply {
        add(post1)
        add(post2)
        add(post3)
    }

}