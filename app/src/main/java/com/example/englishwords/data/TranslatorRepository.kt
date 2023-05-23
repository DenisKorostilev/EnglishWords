package com.example.englishwords.data

import com.example.englishwords.data.`interface`.RetrofitWord
import com.example.englishwords.data.`interface`.TranslateApi
import com.example.englishwords.presentation.TranslateDTO
import com.example.englishwords.presentation.TranslationRequestBody

class TranslatorRepository {
    private val wordsTranslateApi =
        RetrofitWord.getTranslateInstance().create(TranslateApi::class.java)

    suspend fun getTranslation(text: String): String {

        val translateResult =
            wordsTranslateApi.getWordTranslateResults(TranslationRequestBody(text))
        if (translateResult.isSuccessful) {
            val body = translateResult.body()
            if (body != null) {
                return body.text
            }
        }
        return ""
    }
}
