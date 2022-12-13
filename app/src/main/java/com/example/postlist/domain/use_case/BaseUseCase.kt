package com.example.postlist.domain.use_case

import com.example.postlist.R
import com.example.postlist.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

open class BaseUseCase {
    fun <T> handleData(
        func: suspend () -> T
    ): Flow<Resource<T>> = flow {
        try {
            emit(Resource.Loading<T>())
            val result = func.invoke()
            emit(Resource.Success<T>(result))
        } catch (e: HttpException) {
            emit(Resource.Error<T>(e.localizedMessage, R.string.http_exception_message))
        } catch (e: IOException) {
            emit(Resource.Error<T>(messageId = R.string.io_exception_message))
        }
    }
}