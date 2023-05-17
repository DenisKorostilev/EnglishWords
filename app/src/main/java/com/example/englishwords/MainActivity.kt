package com.example.englishwords

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val textView = findViewById<TextView>(R.id.textView)
        val button = findViewById<Button>(R.id.bum)
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://wordsapiv1.p.rapidapi.com/words/hatchback")
            .addHeader("X-RapidAPI-Key", "376cf5ba7fmsh6b7130881991d28p1db30fjsn29a446ee05cd")
            .addHeader("X-RapidAPI-Host", "wordsapiv1.p.rapidapi.com")
            .build()

        button.setOnClickListener() {
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(call: Call, response: Response) {
                    textView.post { textView.text = response.body?.string() }
                }
            })
        }

    }
}
