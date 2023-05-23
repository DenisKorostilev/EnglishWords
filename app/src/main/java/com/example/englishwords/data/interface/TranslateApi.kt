package com.example.englishwords.data.`interface`


import com.example.englishwords.presentation.TranslateDTO
import com.example.englishwords.presentation.TranslationRequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface TranslateApi {
    @POST("translate")
    suspend fun getWordTranslateResults(@Body body: TranslationRequestBody) : Response<TranslateDTO>
}