package com.example.englishwords.data

import android.util.Log
import com.example.englishwords.presentation.TranslateDTO
import com.google.gson.Gson
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

class TranslatorRepository {
    fun getTranslation(text: String, callback : (String?) -> Unit) {
        val client = OkHttpClient()

        val body = JSONObject()
            .put("text", "$text")
            .put("source","EN")
            .put("target","RU")
            .toString()
            .toRequestBody("application/json".toMediaTypeOrNull())

        val request = Request.Builder()
            .url("https://deepl-translator.p.rapidapi.com/translate")
            .addHeader("Content-Type","application/json")
            .addHeader("X-RapidAPI-Key", "376cf5ba7fmsh6b7130881991d28p1db30fjsn29a446ee05cd")
            .addHeader("X-RapidAPI-Host", "deepl-translator.p.rapidapi.com")
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                val translation = Gson().fromJson(body, TranslateDTO::class.java)
                callback(translation.text)
            }

        })
    }
}