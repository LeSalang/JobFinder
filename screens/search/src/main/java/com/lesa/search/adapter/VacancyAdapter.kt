package com.lesa.search.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.lesa.search.R
import com.lesa.ui_kit.databinding.ViewVacancyItemBinding
import com.lesa.vacancy.VacancyUI
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class VacancyAdapter(
    private val onClick: (VacancyUI) -> Unit
) : RecyclerView.Adapter<VacancyAdapter.VacancyViewHolder>() {

    internal var data: List<VacancyUI> = emptyList()
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
    ): VacancyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(com.lesa.ui_kit.R.layout.view_vacancy_item, parent, false)
        return VacancyViewHolder(view = view, onClick = onClick)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(
        holder: VacancyViewHolder,
        position: Int,
    ) {
        val vacancy = data[position]
        holder.bind(vacancy)
    }

    class VacancyViewHolder(view: View, val onClick: (VacancyUI) -> Unit) : RecyclerView.ViewHolder(view) {
        private val binding = ViewVacancyItemBinding.bind(itemView)

        @Suppress("LongMethod")
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(vacancy: VacancyUI) {
            binding.root.setOnClickListener {
                onClick(vacancy)
            }

            if (vacancy.lookingNumber != null) {
                binding.vacancyViewers.visibility = View.VISIBLE
                binding.vacancyViewers.text = itemView.resources.getQuantityString(
                    com.lesa.ui_kit.R.plurals.viewing_people, vacancy.lookingNumber!!,
                    vacancy.lookingNumber
                )
            } else {
                binding.vacancyViewers.visibility = View.GONE
            }

            if (vacancy.isFavorite == true) {
                binding.ivFavorite.setImageResource(com.lesa.ui_kit.R.drawable.ic_favourites_active)
            } else {
                binding.ivFavorite.setImageResource(com.lesa.ui_kit.R.drawable.ic_favourites)
            }

            if (vacancy.title != null) {
                binding.vacancyTitle.visibility = View.VISIBLE
                binding.vacancyTitle.text = vacancy.title
            } else {
                binding.vacancyTitle.visibility = View.GONE
            }

            if (vacancy.fullSalary != null) {
                binding.vacancySalary.visibility = View.VISIBLE
                binding.vacancySalary.text = vacancy.fullSalary
            } else {
                binding.vacancySalary.visibility = View.GONE
            }

            if (vacancy.town != null) {
                binding.vacancyTown.visibility = View.VISIBLE
                binding.vacancyTown.text = vacancy.town
            } else {
                binding.vacancyTown.visibility = View.GONE
            }

            if (vacancy.company != null) {
                binding.vacancyCompany.visibility = View.VISIBLE
                binding.vacancyCheck.visibility = View.VISIBLE
                binding.vacancyCompany.text = vacancy.company
            } else {
                binding.vacancyCompany.visibility = View.GONE
                binding.vacancyCheck.visibility = View.GONE
            }

            if (vacancy.experiencePreviewText != null) {
                binding.vacancyExperience.visibility = View.VISIBLE
                binding.ivVacancyExperience.visibility = View.VISIBLE
                binding.vacancyExperience.text = vacancy.experiencePreviewText
            } else {
                binding.vacancyExperience.visibility = View.GONE
                binding.ivVacancyExperience.visibility = View.GONE
            }

            if (vacancy.publishedDate != null) {
                val local = Locale.getDefault()
                val dateString = formatPublishedDate(
                    itemView.context,
                    vacancy.publishedDate!!,
                    local
                )
                binding.vacancyDate.visibility = View.VISIBLE
                binding.vacancyDate.text = dateString
            } else {
                binding.vacancyDate.visibility = View.GONE
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun formatPublishedDate(context: Context, dateString: String, locale: Locale): String {
    val localDate = LocalDate.parse(dateString)
    val formattedDate = localDate.format(
        DateTimeFormatter.ofPattern("dd MMMM", locale)
    )

    val prefix = context.getString(com.lesa.ui_kit.R.string.published_prefix)

    return "$prefix $formattedDate"
}
