package com.example.englishwords.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.englishwords.R
import com.google.android.material.card.MaterialCardView

class ResultAdapter(
    private var dataSet: MutableList<ResultViewItem>,
    private val clickListener: (ResultViewItem) -> Unit
) :

    RecyclerView.Adapter<ResultAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val definitionTextView: TextView
        val partOfSpeechTextView: TextView
        val synonymsTextView: TextView
        val definitionTranslationTextView: TextView
        val partOfSpeechTranslationTextView: TextView
        val synonymsTranslationTextView: TextView


        val root: MaterialCardView

        init {
            definitionTextView = view.findViewById(R.id.definitionTextView)
            partOfSpeechTextView = view.findViewById(R.id.partOfSpeechTextView)
            synonymsTextView = view.findViewById(R.id.synonymsTextView)
            root = view.findViewById(R.id.root)
            definitionTranslationTextView = view.findViewById(R.id.definitionTranslationTextView)
            partOfSpeechTranslationTextView =
                view.findViewById(R.id.partOfSpeechTranslationTextView)
            synonymsTranslationTextView = view.findViewById(R.id.synonymsTranslationTextView)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.result_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val resultViewItem = dataSet[position]
        viewHolder.definitionTextView.text = "Definition: ${resultViewItem.definition}"
        viewHolder.partOfSpeechTextView.text = "PartOfSpeech: ${resultViewItem.partOfSpeech}"
        viewHolder.synonymsTextView.text = "Synonyms: ${resultViewItem.synonyms}"
        viewHolder.definitionTranslationTextView.text =
            "Определение: ${resultViewItem.definitionTranslation}"
        viewHolder.partOfSpeechTranslationTextView.text =
            "Часть речи: ${resultViewItem.partOfSpeechTranslation}"
        viewHolder.synonymsTranslationTextView.text =
            "Синонимы: ${resultViewItem.synonymsTranslation}"

        viewHolder.root.setOnClickListener {
            clickListener(resultViewItem)
        }
    }

    override fun getItemCount() = dataSet.size
    fun clearData() {
        dataSet.clear()
        notifyDataSetChanged()
    }

    fun setDataItem(item: ResultViewItem) {
        dataSet.add(item)
        notifyDataSetChanged()
    }

    fun setData(items: List<ResultViewItem>) {
        dataSet.clear()
        dataSet.addAll(items)
        notifyDataSetChanged()
    }
}
