package com.example.postlist.data.repository

import com.example.MockData
import com.example.postlist.data.remote.PostApi
import com.example.postlist.domain.repository.PostListRepository
import io.mockk.coEvery
import io.mockk.mockk
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
class PostListRepositoryImplTest {

    private var postApi = mockk<PostApi>()

    private lateinit var postListRepository: PostListRepository

    @Before
    fun setup() {
        postListRepository = PostListRepositoryImpl(postApi)
    }

    @Test
    fun `get users success when repository returns userList`() = runTest {
        coEvery {
            postApi.getUserList()
        } returns MockData.userList

        assertThat(postListRepository.getUserList()).isEqualTo(MockData.userList)
    }

    @Test
    fun `get posts success when repository returns postList`() = runTest {
        coEvery {
            postApi.getPostList()
        } returns MockData.postList

        assertThat(postListRepository.getPostList()).isEqualTo(MockData.postList)

    }

}