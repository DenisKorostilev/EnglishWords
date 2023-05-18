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
    var definition: String,
    var definitionTranslation: String,
    var partOfSpeech: String,
    var partOfSpeechTranslation: String,
    var synonyms: String,
    var synonymsTranslation: String,
)


data class SyllablesDTO(
    val count: Int,
    val list: List<String>
)

data class PronunciationDTO(
    val all: String
)