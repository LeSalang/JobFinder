package com.lesa.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OfferDTO(
    @SerialName("id") val id: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("button") val button: ButtonDTO? = null,
    @SerialName("link") val link: String? = null,
) {
    @Serializable
    data class ButtonDTO(
        @SerialName("text") val text: String? = null,
    )
}
