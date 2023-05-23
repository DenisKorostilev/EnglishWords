package com.example.englishwords.data.`interface`

import okhttp3.Interceptor
import okhttp3.OkHttpClient

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitWord {
    private const val baseUrl = "https://wordsapiv1.p.rapidapi.com/"
    fun getInstance(): Retrofit {
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.addInterceptor(Interceptor {
            val original = it.request()
            val request = original.newBuilder()
                .header("X-RapidAPI-Key", "376cf5ba7fmsh6b7130881991d28p1db30fjsn29a446ee05cd")
                .header("X-RapidAPI-Host", "wordsapiv1.p.rapidapi.com")
                .build()
            it.proceed(request)
        })
        val client = clientBuilder.build()
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}