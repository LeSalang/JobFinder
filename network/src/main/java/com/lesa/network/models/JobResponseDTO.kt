package com.lesa.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JobResponseDTO(
    @SerialName("offers") val offers: List<OfferDTO>,
    @SerialName("vacancies") val vacancies: List<VacancyDTO>,

)
