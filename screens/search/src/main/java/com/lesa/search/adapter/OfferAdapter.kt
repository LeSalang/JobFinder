package com.lesa.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lesa.search.R
import com.lesa.search.databinding.ViewRecommendationItemBinding
import com.lesa.search.models.OfferUI

class OfferAdapter : RecyclerView.Adapter<OfferAdapter.OfferViewHolder>() {

    internal var data: List<OfferUI> = emptyList()
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
    ): OfferViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_recommendation_item, parent, false)
        return OfferViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: OfferViewHolder,
        position: Int,
    ) {
        val offer = data[position]
        holder.bind(offer)
    }

    class OfferViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ViewRecommendationItemBinding.bind(itemView)

        fun bind(offer: OfferUI) {
            if (offer.title != null) {
                binding.recommendationTitle.visibility = View.VISIBLE
                binding.recommendationTitle.text = offer.title
            } else {
                binding.recommendationTitle.visibility = View.GONE
            }

            if (offer.buttonText != null) {
                binding.recommendationBtnText.visibility = View.VISIBLE
                binding.recommendationBtnText.text = offer.buttonText
                binding.recommendationTitle.maxLines = 2
            } else {
                binding.recommendationBtnText.visibility = View.GONE
                binding.recommendationTitle.maxLines = 3
            }

            if (offer.iconType != null) {
                binding.icRecommendation.visibility = View.VISIBLE
                binding.icRecommendation.setImageResource(offer.iconType.resourceId)
            } else {
                binding.icRecommendation.visibility = View.INVISIBLE
            }
        }
    }
}
