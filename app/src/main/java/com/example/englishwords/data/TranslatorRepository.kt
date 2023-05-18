package com.example.englishwords.data

import android.util.Log
import com.example.englishwords.presentation.Root
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class TranslatorRepository {
    fun getTranslation(text: String) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://deepl-translator.p.rapidapi.com/translate")
            .addHeader("X-RapidAPI-Key", "376cf5ba7fmsh6b7130881991d28p1db30fjsn29a446ee05cd")
            .addHeader("X-RapidAPI-Host", "deepl-translator.p.rapidapi.com")
            .post(FormBody.Builder()
                .add("text", "$text")
                .add("source","EN")
                .add("target","RU")
                .build()
            )
            .build()


        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                Log.d("test", "Проверка: $body")
            }

        })
    }
}