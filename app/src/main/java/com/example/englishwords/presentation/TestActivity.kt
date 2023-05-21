package com.example.englishwords.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.englishwords.R
import com.example.englishwords.databinding.TestActivityBinding

class TestActivity : AppCompatActivity(R.layout.test_activity) {
    private val binding: TestActivityBinding by viewBinding()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}