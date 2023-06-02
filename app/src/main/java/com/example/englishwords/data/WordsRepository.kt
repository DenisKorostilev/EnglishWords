package com.example.englishwords.data

import com.example.englishwords.data.`interface`.WordsApi
import com.example.englishwords.data.`interface`.RetrofitWord
import com.example.englishwords.presentation.ResultDTO

class WordsRepository {

    private val wordsApi = RetrofitWord.getWordsInstance().create(WordsApi::class.java)
    suspend fun getWordData(word: String): NetworkResult<List<ResultDTO>> {
        val result = wordsApi.getWordResults(word)
        if (result.isSuccessful) {
            val body = result.body()
            if (body != null) {
                return NetworkResult.Success(body.results)
            }
        }
        return NetworkResult.Error("Ошибка соединения с сервером")
    }
}

sealed class NetworkResult<T> {
    data class Success<T>(val result: T) : NetworkResult<T>()
    data class Error<T>(val errorText: String) : NetworkResult<T>()
}