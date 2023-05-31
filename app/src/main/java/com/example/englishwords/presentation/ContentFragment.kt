package com.example.englishwords.presentation

import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.englishwords.R
import com.example.englishwords.databinding.FragmentContentBinding

class ContentFragment : Fragment(R.layout.fragment_content) {

    private val binding: FragmentContentBinding by viewBinding()
    private val viewModel: ContentViewModel by viewModels()
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
                adapter.clearData()
                viewModel.receiveResults(binding.editText.text.toString())
            }
        }
    }


    private fun bindViews() {
        viewModel.resultViewItems.observe(viewLifecycleOwner) {
            adapter.setDataItem(it)
            binding.swipeContainer.isRefreshing = true
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