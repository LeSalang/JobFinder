package com.lesa.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lesa.data.JobRepository
import com.lesa.search.models.VacancyUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: JobRepository
) : ViewModel() {

    private val _vacancies = MutableStateFlow(emptyList<VacancyUI>())
    val vacancies = _vacancies
    init {
        getAllVacancies()
    }

    fun getAllVacancies() {
        viewModelScope.launch {
            _vacancies.value = repository.getAllVacancies().map {
                it.toVacancyUI()
            }
            Log.d("SearchViewModel", "getAllVacancies: ${_vacancies.value}")
        }
    }
}
