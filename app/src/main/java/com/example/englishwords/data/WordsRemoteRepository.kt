package com.example.englishwords.data

import com.example.englishwords.presentation.ResultDTO
import com.example.englishwords.presentation.RootDTO
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import okhttp3.*
import java.io.FileReader
import java.io.IOException

class WordsRemoteRepository {
    fun getWordData(word: String, callBack: (List<ResultDTO>) -> Unit) {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://wordsapiv1.p.rapidapi.com/words/$word")
            .addHeader("X-RapidAPI-Key", "376cf5ba7fmsh6b7130881991d28p1db30fjsn29a446ee05cd")
            .addHeader("X-RapidAPI-Host", "wordsapiv1.p.rapidapi.com")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                val result = Gson().fromJson(body, RootDTO::class.java)
                callBack(result.results)
            }

        })
    }

//    fun getWordData(word: String, callBack: (List<ResultDTO>) -> Unit) {
//        val result = Gson().fromJson(response, RootDTO::class.java)
//        callBack(result.results)
//    }

    private val response = """
        {
          "word": "hatchback",
          "results": [
            {
              "definition": "a sloping rear car door that is lifted to open",
              "partOfSpeech": "noun",
              "synonyms": [
                "hatch",
                "hatchback door",
                "liftgate"
              ],
              "typeOf": [
                "car door"
              ]
            },
            {
              "definition": "a car having a hatchback door",
              "partOfSpeech": "noun",
              "typeOf": [
                "auto",
                "automobile",
                "car",
                "machine",
                "motorcar"
              ]
            }
          ],
          "syllables": {
            "count": 2,
            "list": [
              "hatch",
              "back"
            ]
          },
          "pronunciation": {
            "all": "'hætʃbæk"
          },
          "frequency": 1.97
        }
    """.trimIndent()
}