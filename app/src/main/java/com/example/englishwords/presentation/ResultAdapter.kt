package com.example.englishwords.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.englishwords.R


class ResultAdapter(private var dataSet: List<Result>) :
    RecyclerView.Adapter<ResultAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val definitionTextView: TextView
        val partOfSpeechTextView: TextView
        val synonymsTextView: TextView

        init {
            // Define click listener for the ViewHolder's View
            definitionTextView = view.findViewById(R.id.definitionTextView)
            partOfSpeechTextView = view.findViewById(R.id.partOfSpeechTextView)
            synonymsTextView = view.findViewById(R.id.synonymsTextView)
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
        viewHolder.definitionTextView.text = "Definition: ${dataSet[position].definition}"
        viewHolder.partOfSpeechTextView.text = "PartOfSpeech: ${dataSet[position].partOfSpeech}"
        viewHolder.synonymsTextView.text = "Synonyms: ${dataSet[position].synonyms?.joinToString (separator = ", ")}"
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    fun setData(dataSet: List<Result>){
        this.dataSet = dataSet
        notifyDataSetChanged()
    }
}
