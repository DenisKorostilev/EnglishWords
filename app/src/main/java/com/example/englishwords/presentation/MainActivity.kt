package com.example.englishwords.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.englishwords.R
import com.example.englishwords.data.TranslatorRepository
import com.example.englishwords.data.WordsRemoteRepository
import com.example.englishwords.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by viewBinding()
    private val wordsRepository = WordsRemoteRepository()
    private val translatorRepository = TranslatorRepository()

    companion object Keys {
        const val RESULT_VIEW_ITEM_KEY = "RESULT_VIEW_ITEM_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val adapter = ResultAdapter(mutableListOf()) { resultViewItem ->
            val intent = Intent(this, TestActivity::class.java)
            intent.putExtra(RESULT_VIEW_ITEM_KEY,resultViewItem )
//            intent.putExtra(DEFINITION_KEY, resultViewItem.definition)
//            intent.putExtra(PART_OF_SPEECH_KEY, resultViewItem.partOfSpeech)
//            intent.putExtra(SYNONYMS_KEY, resultViewItem.synonyms)
//            intent.putExtra(DEFINITION_TRANSLATION_KEY, resultViewItem.definitionTranslation)
//            intent.putExtra(PART_OF_SPEECH_TRANSLATION_KEY, resultViewItem.partOfSpeechTranslation)
//            intent.putExtra(SYNONYMS_TRANSLATION_KEY, resultViewItem.synonymsTranslation)
            startActivity(intent)

        }
        binding.recyclerView.adapter = adapter


        binding.button.setOnClickListener {
            adapter.clearData()

            wordsRepository.getWordData(binding.editText.text.toString()) { results: List<ResultDTO> ->
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
                                runOnUiThread { adapter.setDataItem(resultViewItem) }
                            }
                        }
                    }
                }
            }
        }

    }
}
