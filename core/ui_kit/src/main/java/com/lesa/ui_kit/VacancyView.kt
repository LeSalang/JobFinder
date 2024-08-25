package com.lesa.ui_kit

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout

class VacancyView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val ivFavorite: ImageView
    private val vacancyViewers: TextView
    private val vacancyTitle: TextView
    private val vacancySalary: TextView
    private val vacancyTown: TextView
    private val vacancyCompany: TextView
    private val vacancyExperience: TextView
    private val vacancyDate: TextView
    private val respondVacancyBtn: AppCompatButton

    init {
        LayoutInflater.from(context).inflate(R.layout.view_vacancy_item, this, true)

        ivFavorite = findViewById(R.id.ivFavorite)
        vacancyViewers = findViewById(R.id.vacancyViewers)
        vacancyTitle = findViewById(R.id.vacancyTitle)
        vacancySalary = findViewById(R.id.vacancySalary)
        vacancyTown = findViewById(R.id.vacancyTown)
        vacancyCompany = findViewById(R.id.vacancyCompany)
        vacancyExperience = findViewById(R.id.vacancyExperience)
        vacancyDate = findViewById(R.id.vacancyDate)
        respondVacancyBtn = findViewById(R.id.respondVacancyBtn)
    }

    fun setVacancyTitle(title: String) {
        vacancyTitle.text = title
    }

    fun setVacancySalary(salary: String?) {
        vacancySalary.visibility = if (salary.isNullOrEmpty()) View.GONE else View.VISIBLE
        vacancySalary.text = salary
    }

    fun setVacancyTown(town: String) {
        vacancyTown.text = town
    }

    fun setVacancyCompany(company: String) {
        vacancyCompany.text = company
    }

    fun setVacancyExperience(experience: String) {
        vacancyExperience.text = experience
    }

    fun setVacancyDate(date: String) {
        vacancyDate.text = date
    }

    fun setVacancyViewers(lookingNumber: Int?) {
        if (lookingNumber != null) {
            vacancyViewers.visibility = View.VISIBLE
            vacancyViewers.text = context.resources.getQuantityString(
                R.plurals.viewing_people, lookingNumber, lookingNumber
            )
        } else {
            vacancyViewers.visibility = View.GONE
        }
    }

    fun setFavorite(isFavorite: Boolean) {
        ivFavorite.setImageResource(
            if (isFavorite) {
                R.drawable.ic_favourites_active
            } else {
                R.drawable.ic_favourites
            }
        )
    }

    fun setOnRespondClickListener(listener: OnClickListener) {
        respondVacancyBtn.setOnClickListener(listener)
    }

    fun setRespondButtonEnabled(enabled: Boolean) {
        respondVacancyBtn.isEnabled = enabled
        val textColor = if (enabled) R.color.white else R.color.grey_1
        respondVacancyBtn.setTextColor(resources.getColor(textColor, context.theme))
    }
}
