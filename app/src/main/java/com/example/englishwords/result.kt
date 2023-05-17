package com.example.englishwords


data class Root(
    val word: String,
    val results: List<Result>
)

data class Result(
    val definition: String,
    val partOfSpeech: String,
    val synonyms: List<String>?,
    val typeOf: List<String>
)
