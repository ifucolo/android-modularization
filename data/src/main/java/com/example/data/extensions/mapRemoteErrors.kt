package com.example.data.extensions

import com.example.domain.responses.ResultRemote
import retrofit2.HttpException

fun Throwable.mapRemoteErrors(): ResultRemote.ErrorResponse {
    return when (this) {
        is HttpException -> {
            when (code()) {
                401, 403 -> ResultRemote.ErrorResponse.TokenExpired(this)
                else -> ResultRemote.ErrorResponse.Unknown(this)
            }
        }
        else -> ResultRemote.ErrorResponse.Unknown(this)
    }
}