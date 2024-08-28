package com.lesa.vacancy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lesa.vacancy.databinding.ViewQuestionItemBinding

internal class QuestionAdapter : RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

    internal var data: List<String> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): QuestionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_question_item, parent, false)
        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: QuestionViewHolder,
        position: Int,
    ) {
        val offer = data[position]
        holder.bind(offer)
    }

    class QuestionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ViewQuestionItemBinding.bind(itemView)

        fun bind(question: String) {
            binding.questionBtnText.text = question
        }
    }
}
