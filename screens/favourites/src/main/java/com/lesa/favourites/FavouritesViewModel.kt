package com.lesa.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lesa.data.JobRepository
import com.lesa.vacancy.VacancyUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val repository: JobRepository
) : ViewModel() {

    private val _vacancies = MutableStateFlow(emptyList<VacancyUI>())
    val vacancies = _vacancies

    init {
        getAllVacancies()
    }

    private fun getAllVacancies() {
        viewModelScope.launch {
            _vacancies.value = repository.getAllVacancies()
                .filter {
                    it.isFavorite == true
                }
                .map {
                    it.toVacancyUI()
                }
        }
    }
}
