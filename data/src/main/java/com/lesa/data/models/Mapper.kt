package com.lesa.data.models

import com.lesa.network.models.OfferDTO
import com.lesa.network.models.VacancyDTO

internal fun VacancyDTO.toVacancy(): Vacancy {
    return Vacancy(
        id = id,
        lookingNumber = lookingNumber,
        title = title,
        town = address?.town,
        street = address?.street,
        house = address?.house,
        company = company,
        experiencePreviewText = experience?.previewText,
        experienceText = experience?.text,
        publishedDate = publishedDate,
        isFavorite = isFavorite,
        fullSalary = salary?.full,
        schedules = schedules,
        appliedNumber = appliedNumber,
        description = description,
        responsibilities = responsibilities,
        questions = questions
    )
}

internal fun OfferDTO.toOffer(): Offer {
    return Offer(
        id = id,
        title = title,
        buttonText = button?.text,
        link = link
    )
}
