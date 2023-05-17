package com.example.englishwords.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.englishwords.R
import com.google.android.material.card.MaterialCardView

class ResultAdapter(
    private var dataSet: List<Result>,
    private val clickListener: (definition: String) -> Unit
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

        val root: MaterialCardView

        init {
            // Define click listener for the ViewHolder's View
            definitionTextView = view.findViewById(R.id.definitionTextView)
            partOfSpeechTextView = view.findViewById(R.id.partOfSpeechTextView)
            synonymsTextView = view.findViewById(R.id.synonymsTextView)
            root = view.findViewById(R.id.root)

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
        val result = dataSet[position]

        viewHolder.definitionTextView.text = "Definition: ${result.definition}"
        viewHolder.partOfSpeechTextView.text = "PartOfSpeech: ${result.partOfSpeech}"
        viewHolder.synonymsTextView.text =
            "Synonyms: ${result.synonyms?.joinToString(separator = ", ")}"
        viewHolder.root.setOnClickListener {
            clickListener(result.definition)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    fun setData(dataSet: List<Result>) {
        this.dataSet = dataSet
        notifyDataSetChanged()
    }
}
