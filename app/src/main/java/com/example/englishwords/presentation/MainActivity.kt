package com.example.englishwords.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.englishwords.R
import com.example.englishwords.domain.WordUseCase

class MainActivity : AppCompatActivity() {

    private val useCase: WordUseCase = WordUseCase()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val editText = findViewById<EditText>(R.id.editText)
        val button = findViewById<Button>(R.id.button)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = ResultAdapter(emptyList())
        recyclerView.adapter = adapter


        button.setOnClickListener {
            useCase.getWordData(editText.text.toString()) { root ->
                recyclerView.post {
                    adapter.setData(root.results)
                }
            }
        }

    }
}
