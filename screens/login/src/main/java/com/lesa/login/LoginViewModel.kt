package com.lesa.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor() : ViewModel() {

    private val _isEmailEmpty = MutableLiveData<Boolean>()
    internal val isEmailEmpty: LiveData<Boolean> get() = _isEmailEmpty

    private val _isEmailValid = MutableLiveData<Boolean>()
    internal val isEmailValid: LiveData<Boolean> get() = _isEmailValid

    internal fun onEmailTextChanged(isEmpty: Boolean) {
        _isEmailEmpty.value = isEmpty
    }

    internal fun onResumeButtonClick(email: String) {
        _isEmailValid.value = isEmailValid(email)
    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
