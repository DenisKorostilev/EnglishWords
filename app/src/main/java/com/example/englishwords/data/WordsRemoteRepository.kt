package com.example.englishwords.data

import com.example.englishwords.data.`interface`.WordsApi
import com.example.englishwords.data.`interface`.RetrofitWord
import com.example.englishwords.presentation.ResultDTO

class WordsRemoteRepository {

    private val wordsApi = RetrofitWord.getWordsInstance().create(WordsApi::class.java)
    suspend fun getWordData(word: String): List<ResultDTO> {
        val result = wordsApi.getWordResults(word)
        if (result.isSuccessful) {
            val body = result.body()
            if (body != null) {
                return body.results
            }
        }
        return emptyList()
    }
}