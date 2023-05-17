package com.example.englishwords


data class Root(
    val word: String,
    val results: List<Result>,
    val syllables: Syllables,
    val pronunciation: Pronunciation,
    val frequency: Double
)

data class Result(
    val definition: String,
    val partOfSpeech: String,
    val synonyms: List<String>?,
    val typeOf: List<String>
)

data class Syllables(
    val count: Int,
    val list: List<String>
)

data class Pronunciation(
    val all: String
)