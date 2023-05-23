package com.example.englishwords.data

import com.example.englishwords.data.`interface`.InterfaceWordsApi
import com.example.englishwords.data.`interface`.RetrofitWord
import com.example.englishwords.presentation.ResultDTO
import com.example.englishwords.presentation.RootDTO
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.FileReader
import java.io.IOException

class WordsRemoteRepository {
    fun getWordData(word: String, callBack: (List<ResultDTO>) -> Unit) {
        val interfaceWordsApi = RetrofitWord.getInstance().create(InterfaceWordsApi::class.java)

        GlobalScope.launch {
            val result = interfaceWordsApi.getResults(word)
            if (result.isSuccessful) {
                val body = result.body()
                if (body != null) {
                    callBack(body.results)
                }
            }
        }
    }
}