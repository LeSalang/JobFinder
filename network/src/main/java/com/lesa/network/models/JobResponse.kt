package com.lesa.network.models

data class JobResponse(
    val vacancies: List<Vacancy>,
    val offers: List<Offer>
)
