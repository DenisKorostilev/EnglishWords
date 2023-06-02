package com.example.englishwords.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.englishwords.R
import com.example.englishwords.databinding.FragmentTestBinding
import com.example.englishwords.presentation.ContentFragment.Keys.RESULT_VIEW_ITEM_KEY

class TestFragment : Fragment(R.layout.fragment_test) {
    private val binding: FragmentTestBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val resultViewItem = arguments?.getParcelable<ResultViewItem>(RESULT_VIEW_ITEM_KEY)
        with(binding) {
            definitionTextView.text = resultViewItem?.definition
            definitionTranslationTextView.text = resultViewItem?.definitionTranslation
            partOfSpeechTextView.text = resultViewItem?.partOfSpeech
            partOfSpeechTranslationTextView.text = resultViewItem?.partOfSpeechTranslation
            synonymsTextView.text = resultViewItem?.synonyms
            synonymsTranslationTextView.text = resultViewItem?.synonymsTranslation
        }
    }
}