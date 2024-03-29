package com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.lessons.part18mvvm.login.domain

import com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.lessons.part18mvvm.login.data.LoginRepository

class LoginUseCase {

    private val repository = LoginRepository()

    suspend operator fun invoke(user: String, password: String): Boolean =
        repository.doLogin(user, password)

}