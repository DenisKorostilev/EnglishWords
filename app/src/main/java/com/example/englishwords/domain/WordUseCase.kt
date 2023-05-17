package com.example.englishwords.domain

import com.example.englishwords.data.WordsRemoteRepository
import com.example.englishwords.presentation.Root

class WordUseCase {
    private val repository: WordsRemoteRepository = WordsRemoteRepository()

    fun getWordData(word: String, callBack: (Root) -> Unit) {
        repository.getWordData(word, callBack)
    }
}