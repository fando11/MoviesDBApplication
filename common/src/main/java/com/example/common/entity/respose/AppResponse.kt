package com.example.common.entity.respose

import okhttp3.ResponseBody

sealed class AppResponse<T>(
    val state : Int = 2,
    val data: T?,
    val error: Exception?,
    val code: Int?,
    val errorBody: ResponseBody?
) {
    companion object{
        fun<T> success(t: T): AppResponse<T> = AppResponseSuccess(t)
        fun<T> errorSystem(exc: Exception): AppResponse<T> =
            AppResponseError(exc, AppResponseError.ERROR_SYSTEM,null)
        fun<T> errorBackend(statusCode: Int, body:ResponseBody?): AppResponse<T> =
            AppResponseError(null, statusCode, body)
        fun<T> loading(): AppResponse<T> = AppResponseLoading()
        const val SUCCESS = 0
        const val ERROR = 1
        const val LOADING = 2
    }

}

class AppResponseSuccess<T>(
    data: T
) : AppResponse<T>(0,data,null,null,null)

class AppResponseError<T>(
    exc : Exception?,
    code: Int,
    responseBody: ResponseBody?
) : AppResponse<T>(1,null,exc,code,responseBody){
    companion object{
        const val ERROR_SYSTEM = -1
    }
}

class AppResponseLoading<T> : AppResponse<T>(2,null,null,null,null)