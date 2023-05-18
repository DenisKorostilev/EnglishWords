package com.example.englishwords.presentation


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

data class ResultViewItem(
    val definition: String,
    val definitionTranslation: String,
    val partOfSpeech: String,
    val partOfSpeechTranslation: String,
    val synonyms: List<String>?,
    val synonymsTranslation: List<String>?,
)


data class SyllablesDTO(
    val count: Int,
    val list: List<String>
)

data class PronunciationDTO(
    val all: String
)