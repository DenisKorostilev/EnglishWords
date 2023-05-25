package com.example.englishwords.data.`interface`

import com.example.englishwords.presentation.RootDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WordsApi {
    @GET("words/{word}")
    suspend fun getWordResults(@Path ("word") word:String) : Response<RootDTO>
}