package com.example.common.`object`

import android.content.Context
import android.util.Log
import com.ashokvarma.gander.Gander
import com.ashokvarma.gander.GanderInterceptor
import com.ashokvarma.gander.imdb.GanderIMDB
//import com.example.common.interceptor.ConnectivityInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.internal.connection.ConnectInterceptor
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    fun getClient(context : Context): Retrofit {
        Gander.setGanderStorage(GanderIMDB.getInstance())
        val client = OkHttpClient().newBuilder()
            .addInterceptor(GanderInterceptor(context).showNotification(true))
            .build()
        return Retrofit.Builder()
            .client(client)
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
    }

}