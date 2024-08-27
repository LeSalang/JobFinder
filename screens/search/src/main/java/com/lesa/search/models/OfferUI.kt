package com.lesa.search.models

import com.lesa.search.R

data class OfferUI(
    val id: String?,
    val title: String?,
    val buttonText: String?,
    val iconType: IconType?,
    val link: String?,
) {
    enum class IconType(val resourceId: Int) {
        LEVEL_UP_RESUME(R.drawable.ic_level_up_resume),
        NEAR_VACANCIES(R.drawable.ic_near_vacancies),
        TEMPORARY_JOB(R.drawable.ic_temporary_job),
    }
}
