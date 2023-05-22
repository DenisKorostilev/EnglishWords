package com.example.englishwords.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.englishwords.R
import com.example.englishwords.data.TranslatorRepository
import com.example.englishwords.data.WordsRemoteRepository
import com.example.englishwords.databinding.FragmentContentBinding

class ContentFragment : Fragment(R.layout.fragment_content) {

    private val binding: FragmentContentBinding by viewBinding()
    private val viewModel: ContentViewModel by viewModels()
    private val wordsRepository = WordsRemoteRepository()
    private val translatorRepository = TranslatorRepository()

    companion object Keys {
        const val RESULT_VIEW_ITEM_KEY = "RESULT_VIEW_ITEM_KEY"
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
            viewModel.receiveResults(binding.editText.text.toString())
        }

        viewModel.resultViewItems.observe(viewLifecycleOwner) {
            if (it != null) adapter.setDataItem(it)
        }
    }
}