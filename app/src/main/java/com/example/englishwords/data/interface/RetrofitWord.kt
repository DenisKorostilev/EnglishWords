package com.example.englishwords.data.`interface`

import okhttp3.OkHttpClient

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitWord {
    private const val wordsBaseUrl = "https://wordsapiv1.p.rapidapi.com/"
    private const val wordsTranslateBaseUrl = "https://deepl-translator.p.rapidapi.com/"

    fun getWordsInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(wordsBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor {
                        val newRequest = it.request().newBuilder()
                            .header("X-RapidAPI-Key", "376cf5ba7fmsh6b7130881991d28p1db30fjsn29a446ee05cd")
                            .header("X-RapidAPI-Host", "wordsapiv1.p.rapidapi.com")
                            .build()
                        it.proceed(newRequest)
                    }
                    .build()
            )
            .build()
    }

    fun getTranslateInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(wordsTranslateBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor {
                        val newRequest = it.request().newBuilder()
                            .header("Content-Type","application/json")
                            .header("X-RapidAPI-Key", "376cf5ba7fmsh6b7130881991d28p1db30fjsn29a446ee05cd")
                            .header("X-RapidAPI-Host", "deepl-translator.p.rapidapi.com")
                            .build()
                        it.proceed(newRequest)
                    }
                    .build()
            )
            .build()
    }
}