package com.example.englishwords.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.englishwords.R
import com.example.englishwords.data.TranslatorRepository
import com.example.englishwords.domain.WordUseCase
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val useCase: WordUseCase = WordUseCase()
    private val translatorRepository = TranslatorRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TranslatorRepository().getTranslation("food")
        val root = findViewById<ConstraintLayout>(R.id.root)
        val editText = findViewById<EditText>(R.id.editText)
        val button = findViewById<Button>(R.id.button)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = ResultAdapter(emptyList()) {
            Snackbar.make(root, it, Snackbar.LENGTH_LONG).show()
        }
        recyclerView.adapter = adapter


        button.setOnClickListener {

            useCase.getWordData(editText.text.toString()) { root: RootDTO ->
                translatorRepository.getTranslation(root.results[0].definition)
                root.results.forEach{
                    translatorRepository.getTranslation(it.definition){

                    }
                }
//                recyclerView.post {
//                    adapter.setData(root.results)
//                }
            }
        }


    }
}
