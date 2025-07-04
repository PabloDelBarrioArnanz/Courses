package com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.lessons.part18mvvm.login.ui

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.lessons.part18mvvm.login.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    // private val loginUseCase = LoginUseCase()

    private val _email = MutableLiveData<String>()
    var email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    var password: LiveData<String> = _password

    private val _logging = MutableLiveData<Boolean>()
    var logging: LiveData<Boolean> = _logging

    fun onEmailChange(email: String) {
        _email.value = email
    }

    fun onPasswordChange(password: String) {
        _password.value = password
    }

    fun checkValidLoginData(): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(_email.value ?: "").matches() &&
                (password.value?.length ?: 0) >= 10

    fun onLoginClicked() =
        viewModelScope.launch {
            _logging.value = true
            if (loginUseCase(_email.value!!, _password.value!!)) {
                // navigate to the next view
                Log.i("Demo", "Login success navigating to next view")
            } else {
                Log.e("Demo", "Incorrect user and/or password")
            }
            _logging.value = false
        }

}