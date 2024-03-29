package com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.lessons.part18mvvm.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class LoginViewModel : ViewModel() {

    private val _email = MutableLiveData<String>()
    var email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    var password: LiveData<String> = _password

    fun onEmailChange(email: String) {
        _email.value = email
    }

    fun onPasswordChange(password: String) {
        _password.value = password
    }

    fun checkValidLoginData(): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(_email.value ?: "").matches() &&
                (password.value?.length ?: 0) >= 10

}