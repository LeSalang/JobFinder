package com.lesa.search.models

data class VacancyUI(
    val id: String? = null,
    val lookingNumber: Int? = null,
    val title: String? = null,
    val town: String? = null,
    val street: String? = null,
    val house: String? = null,
    val company: String? = null,
    val experiencePreviewText: String? = null,
    val experienceText: String? = null,
    val publishedDate: String? = null,
    val isFavorite: Boolean? = null,
    val fullSalary: String? = null,
    val schedules: List<String>? = null,
    val appliedNumber: Int? = null,
    val description: String? = null,
    val responsibilities: String? = null,
    val questions: List<String>? = null
)
