package com.example.englishwords.presentation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.englishwords.R
import com.example.englishwords.data.TranslatorRepository
import com.example.englishwords.data.WordsRemoteRepository
import com.example.englishwords.databinding.FragmentContentBinding

/*
    1) Добавил в build.gradle(Module) implementation 'androidx.fragment:fragment:1.5.7'
    2) Создал в layout fragment_content.xml и перенес всё с activity_main.xml в него
    3) Создал ContentFragment  и подключил к нему binding(наверн)
    4) Перенес весь код из MainActivity в ContentFragment
    5)
    */
class ContentFragment : Fragment(R.layout.fragment_content) {

    private var _binding: FragmentContentBinding? = null
    private val binding get() = _binding!!
    private val wordsRepository = WordsRemoteRepository()
    private val translatorRepository = TranslatorRepository()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ResultAdapter(mutableListOf()) { resultViewItem ->
            val intent = Intent(this.activity, TestActivity::class.java)
            intent.putExtra(MainActivity.RESULT_VIEW_ITEM_KEY, resultViewItem)
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
                                activity?.runOnUiThread { adapter.setDataItem(resultViewItem) }
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}