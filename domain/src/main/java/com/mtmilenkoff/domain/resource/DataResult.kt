package com.mtmilenkoff.domain.resource

sealed class DataResult<out T> {
    data class Success<out T>(val data: T?) : DataResult<T>()

    data object Loading : DataResult<Nothing>()

    data class Failed(val error: ErrorModel) : DataResult<Nothing>()
}

data class ErrorModel(val code: Int, val message: String)
