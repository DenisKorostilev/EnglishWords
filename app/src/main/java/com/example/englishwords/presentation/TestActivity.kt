package com.example.englishwords.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
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

            intent.extras?.getParcelable(RESULT_VIEW_ITEM_KEY,ResultViewItem::class.java)

//        val definitionTextView = findViewById<TextView>(R.id.definitionTextView)
//        val definitionTranslationTextView =
//            findViewById<TextView>(R.id.definitionTranslationTextView)
//        val partOfSpeechTextView = findViewById<TextView>(R.id.partOfSpeechTextView)
//        val partOfSpeechTranslationTextView =
//            findViewById<TextView>(R.id.partOfSpeechTranslationTextView)
//        val synonymsTextView = findViewById<TextView>(R.id.synonymsTextView)
//        val synonymsTranslationTextView = findViewById<TextView>(R.id.synonymsTranslationTextView)


//        definitionTranslationTextView.text =
//            intent.extras?.getString(MainActivity.DEFINITION_TRANSLATION_KEY)
//        partOfSpeechTextView.text = intent.extras?.getString(MainActivity.PART_OF_SPEECH_KEY)
//        partOfSpeechTranslationTextView.text =
//            intent.extras?.getString(MainActivity.PART_OF_SPEECH_TRANSLATION_KEY)
//        synonymsTextView.text = intent.extras?.getString(MainActivity.SYNONYMS_KEY)
//        synonymsTranslationTextView.text =
//            intent.extras?.getString(MainActivity.SYNONYMS_TRANSLATION_KEY)
    }

}