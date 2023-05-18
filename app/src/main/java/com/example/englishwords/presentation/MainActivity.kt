package com.example.englishwords.presentation

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.example.englishwords.R
import com.example.englishwords.data.TranslatorRepository
import com.example.englishwords.data.WordsRemoteRepository
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val wordsRepository = WordsRemoteRepository()
    private val translatorRepository = TranslatorRepository()

    companion object {
        const val DEFINITION_KEY = "DEFINITION_KEY"
        const val DEFINITION_TRANSLATION_KEY = "DEFINITION_TRANSLATION_KEY"
        const val PART_OF_SPEECH_KEY = "PATROFSPEECH_KEY"
        const val PART_OF_SPEECH_TRANSLATION_KEY = "PART_OF_SPEECH_TRANSLATION_KEY"
        const val SYNONYMS_KEY = "SYNONYMS_KEY"
        const val SYNONYMS_TRANSLATION_KEY = "SYNONYMS_TRANSLATION_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val editText = findViewById<EditText>(R.id.editText)
        val button = findViewById<Button>(R.id.button)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = ResultAdapter(mutableListOf()) { resultViewItem ->
            val intent = Intent(this, TestActivity::class.java)
//                intent.putExtra(bundleOf())
            intent.putExtra(DEFINITION_KEY, resultViewItem.definition)
            intent.putExtra(PART_OF_SPEECH_KEY, resultViewItem.partOfSpeech)
            intent.putExtra(SYNONYMS_KEY, resultViewItem.synonyms)
            intent.putExtra(DEFINITION_TRANSLATION_KEY, resultViewItem.definitionTranslation)
            intent.putExtra(PART_OF_SPEECH_TRANSLATION_KEY, resultViewItem.partOfSpeechTranslation)
            intent.putExtra(SYNONYMS_TRANSLATION_KEY, resultViewItem.synonymsTranslation)
            startActivity(intent)

        }
        recyclerView.adapter = adapter


        button.setOnClickListener {
            adapter.clearData()

            wordsRepository.getWordData(editText.text.toString()) { results: List<ResultDTO> ->
                results.forEach { result ->
                    translatorRepository.getTranslation(result.definition) { definitionTranslation ->
                        val definitionTranslationField = definitionTranslation.orEmpty()

                        translatorRepository.getTranslation(result.partOfSpeech) { partOfSpeechTranslation ->
                            val partOfSpeechTranslationField = partOfSpeechTranslation.orEmpty()

                            translatorRepository.getTranslation(
                                result.synonyms?.joinToString(". ") ?: "there is no synonyms"
                            ) { synonymsTranslation ->
                                val synonymsTranslationField = synonymsTranslation.orEmpty()

                                val resultViewItem = ResultViewItem(
                                    definition = result.definition,
                                    definitionTranslation = definitionTranslationField,
                                    partOfSpeech = result.partOfSpeech,
                                    partOfSpeechTranslation = partOfSpeechTranslationField,
                                    synonyms = result.synonyms?.joinToString(". ").orEmpty(),
                                    synonymsTranslation = synonymsTranslationField,
                                )
                                recyclerView.post { adapter.setDataItem(resultViewItem) }
                            }
                        }
                    }
                }
            }
        }

    }
}
