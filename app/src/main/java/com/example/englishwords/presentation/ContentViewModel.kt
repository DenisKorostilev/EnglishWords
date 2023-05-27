package com.example.englishwords.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.englishwords.data.TranslatorRepository
import com.example.englishwords.data.WordsRemoteRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class ContentViewModel : ViewModel() {

    private val wordsRepository = WordsRemoteRepository()
    private val translatorRepository = TranslatorRepository()
    private val _resultViewItems = MutableLiveData<ResultViewItem?>()
    val resultViewItems: LiveData<ResultViewItem?> = _resultViewItems

    fun receiveResults(text: String) {

        viewModelScope.launch {
            val results = wordsRepository.getWordData(text)
            results.map { result ->
                async {
                    val definitionTranslation =
                        translatorRepository.getTranslation(result.definition)
                    val partOfSpeechTranslation =
                        translatorRepository.getTranslation(result.partOfSpeech)
                    val synonymsTranslation =

                        translatorRepository.getTranslation(
                            result.synonyms?.joinToString(". ") ?: "there is no synonyms"
                        )

                    val resultViewItem = ResultViewItem(
                        definition = result.definition,
                        definitionTranslation = definitionTranslation,
                        partOfSpeech = result.partOfSpeech,
                        partOfSpeechTranslation = partOfSpeechTranslation,
                        synonyms = result.synonyms?.joinToString(". ").orEmpty(),
                        synonymsTranslation = synonymsTranslation
                    )
                    _resultViewItems.postValue(resultViewItem)
                }
            }.awaitAll()
        }
    }
}