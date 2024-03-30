package com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.lessons.part18mvvm.login.data

import com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.lessons.part18mvvm.login.data.network.LoginService
import javax.inject.Inject

class LoginRepository @Inject constructor(private val api: LoginService) {

    // private val api = LoginService()

    suspend fun doLogin(user: String, password: String): Boolean =
        api.doLogin(user, password)

}