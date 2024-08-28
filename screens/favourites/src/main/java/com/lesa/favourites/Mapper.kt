package com.lesa.favourites

import com.lesa.data.models.Vacancy
import com.lesa.vacancy.VacancyUI

internal fun Vacancy.toVacancyUI(): VacancyUI {
    return VacancyUI(
        id = id,
        lookingNumber = lookingNumber,
        title = title,
        town = town,
        street = street,
        house = house,
        company = company,
        experiencePreviewText = experiencePreviewText,
        experienceText = experienceText,
        publishedDate = publishedDate,
        isFavorite = isFavorite,
        fullSalary = fullSalary,
        schedules = schedules,
        appliedNumber = appliedNumber,
        description = description,
        responsibilities = responsibilities,
        questions = questions
    )
}
