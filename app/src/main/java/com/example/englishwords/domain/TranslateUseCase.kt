package com.example.englishwords.domain

import com.example.englishwords.data.NetworkResult
import com.example.englishwords.data.TranslatorRepository

class TranslateUseCase {
    private val translatorRepository = TranslatorRepository()

    suspend fun getTranslation(text: String): String {
        return when (val translationResult = translatorRepository.getTranslation(text)) {
            is NetworkResult.Success -> translationResult.result
            is NetworkResult.Error -> translationResult.errorText
        }
    }
}