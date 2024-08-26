package com.lesa.data.models

import com.lesa.network.models.OfferDTO
import com.lesa.network.models.VacancyDTO

internal fun VacancyDTO.toVacancy(): Vacancy {
    return Vacancy(
        id = id,
        title = title,
        experience = experience,
        employment = employment,
        description = description,
        city = city,
        company = company,
        date = date,
    )
}

internal fun OfferDTO.toOffer(): Offer {
    return Offer(
        id = id,
        title = title,
        description = description,
    )
}
