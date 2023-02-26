package com.example.unsplash.core.datatype

import java.lang.Exception

data class Result<out T>(
    var resultType: ResultType,
    val data: T? = null,
    val error: Exception? = null
) {

    companion object {
        fun <T> success(data: T?): Result<T> {
            return Result(ResultType.SUCCESS, data)
        }

        fun <T> error(error: Exception? = null): Result<T> {
            return Result(ResultType.ERROR, error = error)
        }

        fun <T> loading(): Result<T> {
            return Result(ResultType.LOADING)
        }
    }
}
