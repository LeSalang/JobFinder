package com.lesa.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor() : ViewModel() {

    internal val email = MutableStateFlow("")

    private val _isEmailEmpty = MutableLiveData<Boolean>()
    internal val isEmailEmpty: LiveData<Boolean> get() = _isEmailEmpty

    private val _isEmailValid = MutableLiveData<Boolean>()
    internal val isEmailValid: LiveData<Boolean> get() = _isEmailValid

    internal val openVerificationScreen = MutableSharedFlow<Unit>()

    internal fun onEmailTextChanged(isEmpty: Boolean) {
        _isEmailEmpty.value = isEmpty
    }

    internal fun onResumeButtonClick(email: String) {
        val isValid = isEmailValid(email)
        _isEmailValid.value = isValid
        viewModelScope.launch {
            if (isValid) {
                openVerificationScreen.emit(Unit)
            }
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
