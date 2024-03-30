package com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.lessons.part18mvvm.login.domain

import com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.lessons.part18mvvm.login.data.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: LoginRepository) {

    // private val repository = LoginRepository()

    suspend operator fun invoke(user: String, password: String): Boolean =
        repository.doLogin(user, password)

}