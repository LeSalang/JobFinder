package com.lesa.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lesa.data.JobRepository
import com.lesa.search.models.OfferUI
import com.lesa.vacancy.VacancyUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: JobRepository
) : ViewModel() {

    private val _vacanciesFull = MutableStateFlow(emptyList<VacancyUI>())
    val vacanciesFull = _vacanciesFull

    private val _vacanciesMin = MutableStateFlow(emptyList<VacancyUI>())
    val vacanciesMin = _vacanciesMin

    private val _offers = MutableStateFlow(emptyList<OfferUI>())
    val offers = _offers

    private val _screenMode = MutableStateFlow(ScreenMode.MIN)
    val screenMode = _screenMode

    init {
        getAllVacancies()
        getAllOffers()
    }

    private fun getAllVacancies() {
        viewModelScope.launch {
            _vacanciesFull.value = repository.getAllVacancies().map {
                it.toVacancyUI()
            }
            _vacanciesMin.value = _vacanciesFull.value.subList(fromIndex = 0, toIndex = 3)
        }
    }

    private fun getAllOffers() {
        viewModelScope.launch {
            _offers.value = repository.getAllOffers().map {
                it.toOfferUI()
            }
        }
    }

    fun setFullScreenMode() {
        _screenMode.value = ScreenMode.FULL
    }
}

enum class ScreenMode {
    MIN,
    FULL
}
