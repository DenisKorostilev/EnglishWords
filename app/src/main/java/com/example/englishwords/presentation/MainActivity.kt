package com.example.englishwords.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.englishwords.R
import com.example.englishwords.data.TranslatorRepository
import com.example.englishwords.data.WordsRemoteRepository
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val wordsRepository = WordsRemoteRepository()
    private val translatorRepository = TranslatorRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val root = findViewById<ConstraintLayout>(R.id.root)
        val editText = findViewById<EditText>(R.id.editText)
        val button = findViewById<Button>(R.id.button)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = ResultAdapter(mutableListOf()) {
            Snackbar.make(root, it, Snackbar.LENGTH_LONG).show()
        }
        recyclerView.adapter = adapter


        button.setOnClickListener {
            adapter.clearData()

            wordsRepository.getWordData(editText.text.toString()) { results: List<ResultDTO> ->
                results.forEach { result ->
                    val resultViewItem = ResultViewItem(
                        definition = result.definition,
                        definitionTranslation = "",
                        partOfSpeech = result.partOfSpeech,
                        partOfSpeechTranslation = "",
                        synonyms = result.synonyms?.joinToString(". ").orEmpty(),
                        synonymsTranslation = "",
                    )
                    translatorRepository.getTranslation(result.definition) { definitionTranslation ->
                        resultViewItem.definitionTranslation = definitionTranslation.orEmpty()

                        translatorRepository.getTranslation(result.partOfSpeech) { partOfSpeechTranslation ->
                            resultViewItem.partOfSpeechTranslation = partOfSpeechTranslation.orEmpty()

                            translatorRepository.getTranslation(result.synonyms?.joinToString(". ") ?: "there is no synonyms") { synonymsTranslation ->
                                resultViewItem.synonymsTranslation = synonymsTranslation.orEmpty()

                                recyclerView.post{ adapter.setDataItem(resultViewItem) }
                            }
                        }
                    }
                }
            }
        }
    }
}
