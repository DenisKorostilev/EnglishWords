package com.example.englishwords.presentation

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.englishwords.R
import com.example.englishwords.databinding.FragmentContentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContentFragment : Fragment(R.layout.fragment_content) {

    private val binding: FragmentContentBinding by viewBinding()
    private val viewModel: ContentViewModel by viewModel()
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
                incomingRequests()
            }
            swipeRefreshLayout.setOnRefreshListener {
                incomingRequests()
            }
        }
    }

    private fun incomingRequests() {
        adapter.clearData()
        viewModel.receiveResults(binding.editText.text.toString())
    }

    private fun bindViews() {
        viewModel.screenState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ScreenState.Success -> {
                    adapter.setDataItem(state.resultViewItems)
                    binding.swipeRefreshLayout.isRefreshing = false
                }
                ScreenState.Loading -> {
                    binding.swipeRefreshLayout.isRefreshing = true
                }
                ScreenState.Init -> Unit
                is ScreenState.Error -> {
                    binding.swipeRefreshLayout.isRefreshing = false
                }
            }
        }
    }

    private fun moveToTestFragment(resultViewItem: ResultViewItem) {
        findNavController().navigate(
            ContentFragmentDirections.actionContentFragmentToTestFragment(
                resultViewItem
            )
        )
    }
}