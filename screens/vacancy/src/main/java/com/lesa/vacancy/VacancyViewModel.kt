package com.lesa.vacancy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VacancyViewModel @Inject constructor() : ViewModel() {
    private val _vacancy = MutableLiveData<VacancyUI>()
    internal val vacancy: LiveData<VacancyUI> get() = _vacancy

    internal fun setVacancy(vacancy: VacancyUI) {
        _vacancy.value = vacancy
    }

    internal fun onFavoriteButtonClick() {
        _vacancy.value = _vacancy.value?.copy(isFavorite = !_vacancy.value?.isFavorite!!)
    }
}
