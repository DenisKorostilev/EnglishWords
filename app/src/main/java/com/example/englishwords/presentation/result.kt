package com.example.englishwords.presentation

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class RootDTO(
    val word: String,
    val results: List<ResultDTO>,
    val syllables: SyllablesDTO,
    val pronunciation: PronunciationDTO,
    val frequency: Double
)

data class ResultDTO(
    val definition: String,
    val partOfSpeech: String,
    val synonyms: List<String>?,
    val typeOf: List<String>
)

@Parcelize
data class ResultViewItem(
    var definition: String,
    var definitionTranslation: String,
    var partOfSpeech: String,
    var partOfSpeechTranslation: String,
    var synonyms: String,
    var synonymsTranslation: String,
) : Parcelable

data class SyllablesDTO(
    val count: Int,
    val list: List<String>
)

data class PronunciationDTO(
    val all: String
)

data class TranslationRequestBody(
    val text: String,
    val source: String = "EN",
    val target: String = "RU"
)