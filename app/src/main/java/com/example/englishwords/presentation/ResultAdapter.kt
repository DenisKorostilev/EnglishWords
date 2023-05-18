package com.example.englishwords.presentation

import android.content.Intent
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

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val definitionTextView: TextView
        val partOfSpeechTextView: TextView
        val synonymsTextView: TextView

        val definitionTranslationTextView: TextView
        val partOfSpeechTranslationTextView: TextView
        val synonymsTranslationTextView: TextView

        val root: MaterialCardView

        init {
            // Define click listener for the ViewHolder's View
            definitionTextView = view.findViewById(R.id.definitionTextView)
            partOfSpeechTextView = view.findViewById(R.id.partOfSpeechTextView)
            synonymsTextView = view.findViewById(R.id.synonymsTextView)
            root = view.findViewById(R.id.root)
            definitionTranslationTextView = view.findViewById(R.id.definitionTranslationTextView)
            partOfSpeechTranslationTextView = view.findViewById(R.id.partOfSpeechTranslationTextView)
            synonymsTranslationTextView = view.findViewById(R.id.synonymsTranslationTextView)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.result_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        val resultViewItem = dataSet [position]

        viewHolder.definitionTextView.text = "Definition: ${resultViewItem.definition}"
        viewHolder.partOfSpeechTextView.text = "PartOfSpeech: ${resultViewItem.partOfSpeech}"
        viewHolder.synonymsTextView.text = "Synonyms: ${resultViewItem.synonyms}"

        viewHolder.definitionTranslationTextView.text = "Определение: ${resultViewItem.definitionTranslation}"
        viewHolder.partOfSpeechTranslationTextView.text = "Часть речи: ${resultViewItem.partOfSpeechTranslation}"
        viewHolder.synonymsTranslationTextView.text = "Синонимы: ${resultViewItem.synonymsTranslation}"

        viewHolder.root.setOnClickListener {
            clickListener(resultViewItem)


        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    fun clearData() {
        dataSet.clear()
        notifyDataSetChanged()
    }

    fun setDataItem(item: ResultViewItem) {
        dataSet.add(item)
        notifyDataSetChanged()
    }


}
