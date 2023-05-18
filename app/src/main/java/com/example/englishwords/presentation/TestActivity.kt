package com.example.englishwords.presentation

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.englishwords.R

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_activity)
        val definitionTextView = findViewById<TextView>(R.id.definitionTextView)
        val definitionTranslationTextView =
            findViewById<TextView>(R.id.definitionTranslationTextView)
        val partOfSpeechTextView = findViewById<TextView>(R.id.partOfSpeechTextView)
        val partOfSpeechTranslationTextView =
            findViewById<TextView>(R.id.partOfSpeechTranslationTextView)
        val synonymsTextView = findViewById<TextView>(R.id.synonymsTextView)
        val synonymsTranslationTextView = findViewById<TextView>(R.id.synonymsTranslationTextView)

        definitionTextView.text = intent.extras?.getString(MainActivity.DEFINITION_KEY)
        definitionTranslationTextView.text =
            intent.extras?.getString(MainActivity.DEFINITION_TRANSLATION_KEY)
        partOfSpeechTextView.text = intent.extras?.getString(MainActivity.PART_OF_SPEECH_KEY)
        partOfSpeechTranslationTextView.text =
            intent.extras?.getString(MainActivity.PART_OF_SPEECH_TRANSLATION_KEY)
        synonymsTextView.text = intent.extras?.getString(MainActivity.SYNONYMS_KEY)
        synonymsTranslationTextView.text =
            intent.extras?.getString(MainActivity.SYNONYMS_TRANSLATION_KEY)
    }

}