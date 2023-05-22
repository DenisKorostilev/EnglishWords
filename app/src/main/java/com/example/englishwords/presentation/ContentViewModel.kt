package com.example.englishwords.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.englishwords.data.TranslatorRepository
import com.example.englishwords.data.WordsRemoteRepository

class ContentViewModel : ViewModel() {

    private val wordsRepository = WordsRemoteRepository()
    private val translatorRepository = TranslatorRepository()

    private val _resultViewItems = MutableLiveData<ResultViewItem?>()
    val resultViewItems: LiveData<ResultViewItem?> = _resultViewItems

    fun receiveResults(text: String) {

        wordsRepository.getWordData(text) { results: List<ResultDTO> ->
            results.forEach { result ->
                translatorRepository.getTranslation(result.definition) { definitionTranslation ->
                    val definitionTranslationField = definitionTranslation.orEmpty()

                    translatorRepository.getTranslation(result.partOfSpeech) { partOfSpeechTranslation ->
                        val partOfSpeechTranslationField = partOfSpeechTranslation.orEmpty()

                        translatorRepository.getTranslation(
                            result.synonyms?.joinToString(". ") ?: "there is no synonyms"
                        ) { synonymsTranslation ->
                            val synonymsTranslationField = synonymsTranslation.orEmpty()

                            val resultViewItem = ResultViewItem(
                                definition = result.definition,
                                definitionTranslation = definitionTranslationField,
                                partOfSpeech = result.partOfSpeech,
                                partOfSpeechTranslation = partOfSpeechTranslationField,
                                synonyms = result.synonyms?.joinToString(". ").orEmpty(),
                                synonymsTranslation = synonymsTranslationField,
                            )
//                            activity?.runOnUiThread { adapter.setDataItem(resultViewItem) }

                            _resultViewItems.postValue(resultViewItem)
                        }
                    }
                }
            }
        }

    }

}