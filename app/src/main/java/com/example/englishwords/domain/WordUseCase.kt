package com.example.englishwords.domain

import com.example.englishwords.data.WordsRemoteRepository
import com.example.englishwords.presentation.RootDTO

class WordUseCase {
    private val repository: WordsRemoteRepository = WordsRemoteRepository()

    fun getWordData(word: String, callBack: (RootDTO) -> Unit) {
        repository.getWordData(word, callBack)
    }
}