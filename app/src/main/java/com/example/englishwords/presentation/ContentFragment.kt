package com.example.englishwords.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.englishwords.R
import com.example.englishwords.data.TranslatorRepository
import com.example.englishwords.data.WordsRemoteRepository
import com.example.englishwords.databinding.FragmentContentBinding

class ContentFragment : Fragment(R.layout.fragment_content) {

    private var _binding: FragmentContentBinding? = null
    private val binding get() = _binding!!
    private val wordsRepository = WordsRemoteRepository()
    private val translatorRepository = TranslatorRepository()

    companion object Keys {
        const val RESULT_VIEW_ITEM_KEY = "RESULT_VIEW_ITEM_KEY"
    }

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
            val frag_2 = TestFragment()
            frag_2.arguments = bundleOf(RESULT_VIEW_ITEM_KEY to resultViewItem)
            val ft = parentFragmentManager.beginTransaction()
            ft.replace(R.id.fragment_container_view, frag_2)
            ft.commit()

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