package com.lesa.network.models

data class JobResponseDTO(
    val vacancies: List<VacancyDTO>,
    val offers: List<OfferDTO>
)
