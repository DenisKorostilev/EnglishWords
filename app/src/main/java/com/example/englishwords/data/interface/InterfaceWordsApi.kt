package com.example.englishwords.data.`interface`

import android.telecom.Call
import com.example.englishwords.presentation.RootDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface InterfaceWordsApi {
    @GET("words/{word}")

    suspend fun getResults(@Path ("word") word:String) : Response<RootDTO>
}