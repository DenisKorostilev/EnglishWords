package com.example.englishwords

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
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


        val editText = findViewById<EditText>(R.id.editText)
        val button = findViewById<Button>(R.id.button)
        val client = OkHttpClient()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = ResultAdapter(emptyList())
        recyclerView.adapter = adapter


        button.setOnClickListener {
            val request = Request.Builder()
                .url("https://wordsapiv1.p.rapidapi.com/words/${editText.text}")
                .addHeader("X-RapidAPI-Key", "376cf5ba7fmsh6b7130881991d28p1db30fjsn29a446ee05cd")
                .addHeader("X-RapidAPI-Host", "wordsapiv1.p.rapidapi.com")
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body?.string()
                    val result = Gson().fromJson(body, Root::class.java)
                    recyclerView.post {
                        adapter.setData(result.results)
                    }

                }


            })
        }

    }
}
