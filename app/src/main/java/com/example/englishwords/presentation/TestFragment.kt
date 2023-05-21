package com.example.englishwords.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.englishwords.R
import com.example.englishwords.databinding.FragmentTestBinding

class TestFragment: Fragment(R.layout.fragment_test) {
    private var _binding: FragmentTestBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTestBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val resultViewItem = this.activity?.intent?.extras?.getParcelable<ResultViewItem>(MainActivity.RESULT_VIEW_ITEM_KEY)
        binding.definitionTextView.text = resultViewItem?.definition
        binding.definitionTranslationTextView.text = resultViewItem?.definitionTranslation
        binding.partOfSpeechTextView.text = resultViewItem?.partOfSpeech
        binding.partOfSpeechTranslationTextView.text = resultViewItem?.partOfSpeechTranslation
        binding.synonymsTextView.text = resultViewItem?.synonyms
        binding.synonymsTranslationTextView.text = resultViewItem?.synonymsTranslation
    }
}