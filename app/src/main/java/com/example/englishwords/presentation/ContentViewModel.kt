package com.example.englishwords.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.englishwords.data.NetworkResult
import com.example.englishwords.data.TranslatorRepository
import com.example.englishwords.data.WordsRepository
import com.example.englishwords.domain.TranslateUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class ContentViewModel : ViewModel() {

    private val wordsRepository = WordsRepository()
    private val translatorUseCase = TranslateUseCase()
    private val _screenState = MutableLiveData<ScreenState>(ScreenState.Init)
    val screenState: LiveData<ScreenState> = _screenState

    fun receiveResults(text: String) {
        _screenState.value = ScreenState.Loading
        viewModelScope.launch {
            when (val networkResult = wordsRepository.getWordData(text)) {
                is NetworkResult.Success -> {
                    val resultViewItems = networkResult.result.map { result ->
                        async {
                            val definitionTranslation =
                                async { translatorUseCase.getTranslation(result.definition) }
                            val partOfSpeechTranslation =
                                async { translatorUseCase.getTranslation(result.partOfSpeech) }
                            val synonymsTranslation =

                                async {
                                    translatorUseCase.getTranslation(
                                        result.synonyms?.joinToString(". ")
                                            ?: "there is no synonyms"
                                    )
                                }

                            ResultViewItem(
                                definition = result.definition,
                                definitionTranslation = definitionTranslation.await(),
                                partOfSpeech = result.partOfSpeech,
                                partOfSpeechTranslation = partOfSpeechTranslation.await(),
                                synonyms = result.synonyms?.joinToString(". ").orEmpty(),
                                synonymsTranslation = synonymsTranslation.await()
                            )
                        }
                    }.awaitAll()
                    _screenState.postValue(ScreenState.Success(resultViewItems))
                }

                is NetworkResult.Error -> _screenState.postValue(ScreenState.Error(networkResult.errorText))
            }


        }
    }
}

sealed class ScreenState {
    object Loading : ScreenState()
    data class Success(val resultViewItems: List<ResultViewItem>) : ScreenState()
    data class Error(val errorText: String) : ScreenState()
    object Init : ScreenState()
}