package com.example.englishwords.presentation

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.ProgressBar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.englishwords.R
import com.example.englishwords.databinding.FragmentContentBinding

class ContentFragment : Fragment(R.layout.fragment_content) {

    private val binding: FragmentContentBinding by viewBinding()
    private val viewModel: ContentViewModel by viewModels()
    private var progressBar: ProgressBar? = null
    private val adapter = ResultAdapter(mutableListOf()) { resultViewItem ->
        moveToTestFragment(resultViewItem)
    }

    companion object Keys {
        const val RESULT_VIEW_ITEM_KEY = "RESULT_VIEW_ITEM_KEY"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        bindViews()
    }


    private fun initViews() {
        with(binding) {
            recyclerView.adapter = adapter

            button.setOnClickListener {
                progressBar?.visibility = View.VISIBLE
                adapter.clearData()
                viewModel.receiveResults(binding.editText.text.toString())

            }
        }
    }

    private fun bindViews() {
        viewModel.resultViewItems.observe(viewLifecycleOwner) {
            if (it != null) adapter.setDataItem(it)
        }
    }

    private fun moveToTestFragment(resultViewItem: ResultViewItem) {
        val testFragment = TestFragment()
        testFragment.arguments = bundleOf(RESULT_VIEW_ITEM_KEY to resultViewItem)
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container_view, testFragment)
        transaction.commit()
    }

}