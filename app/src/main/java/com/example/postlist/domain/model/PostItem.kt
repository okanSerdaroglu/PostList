package com.example.postlist.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class PostItem : Parcelable {
    @Parcelize
    data class Image(
        val imageUrl: String
    ) : PostItem()

    @Parcelize
    data class PostDetail(
        val title: String,
        val body: String,
        val userId: Int
    ) : PostItem()
}