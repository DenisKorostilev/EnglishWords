package com.example.englishwords

import android.app.Application
import com.example.englishwords.data.TranslatorRepository
import com.example.englishwords.data.WordsRepository
import com.example.englishwords.data.`interface`.RetrofitWord
import com.example.englishwords.data.`interface`.TranslateApi
import com.example.englishwords.data.`interface`.WordsApi
import com.example.englishwords.domain.TranslateUseCase
import com.example.englishwords.presentation.ContentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module


class EnglishWordsApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val appModule = module {
            single { WordsRepository(get()) }
            single { TranslatorRepository(get()) }
            single { TranslateUseCase(get()) }
            single { RetrofitWord.getTranslateInstance().create(TranslateApi::class.java) }
            single { RetrofitWord.getWordsInstance().create(WordsApi::class.java) }

            viewModel { ContentViewModel(get(), get()) }
        }
        startKoin {
            modules(appModule)
        }
    }
}