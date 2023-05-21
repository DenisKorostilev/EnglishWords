package com.example.englishwords.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.englishwords.R
import com.example.englishwords.databinding.TestActivityBinding
import com.example.englishwords.presentation.MainActivity.Keys.RESULT_VIEW_ITEM_KEY

class TestActivity : AppCompatActivity(R.layout.test_activity) {
    private val binding: TestActivityBinding by viewBinding()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val resultViewItem = intent.extras?.getParcelable<ResultViewItem>(RESULT_VIEW_ITEM_KEY)

        binding.definitionTextView.text = resultViewItem?.definition
        binding.definitionTranslationTextView.text = resultViewItem?.definitionTranslation
        binding.partOfSpeechTextView.text = resultViewItem?.partOfSpeech
        binding.partOfSpeechTranslationTextView.text = resultViewItem?.partOfSpeechTranslation
        binding.synonymsTextView.text = resultViewItem?.synonyms
        binding.synonymsTranslationTextView.text = resultViewItem?.synonymsTranslation
    }
}