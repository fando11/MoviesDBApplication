package com.example.common.entity.respose

import okhttp3.ResponseBody

sealed class AppResponse2<T,S>(
    val state : Int = 2,
    val data: T?,
    val data2: S?,
    val error: Exception?,
    val code: Int?,
    val errorBody: ResponseBody?
) {
    companion object{
        fun<T,S> success(t: T, s:S): AppResponse2<T,S> = AppResponseSuccess2(t,s)
        fun<T,S> errorSystem(exc: Exception): AppResponse2<T,S> =
            AppResponseError2(exc, AppResponseError.ERROR_SYSTEM,null)
        fun<T,S> errorBackend(statusCode: Int, body: ResponseBody?): AppResponse2<T,S> =
            AppResponseError2(null, statusCode, body)
        fun<T,S> loading(): AppResponse2<T,S> = AppResponseLoading2()
    }

}

class AppResponseSuccess2<T,S>(
    data: T,
    data2: S
) : AppResponse2<T,S>(0,data,data2,null,null,null)

class AppResponseError2<T,S>(
    exc : Exception?,
    code: Int,
    responseBody: ResponseBody?
) : AppResponse2<T,S>(1,null,null,exc,code,responseBody){
    companion object{
        const val ERROR_SYSTEM = -1
    }
}

class AppResponseLoading2<T,S> : AppResponse2<T,S>(2,null,null,null,null,null)