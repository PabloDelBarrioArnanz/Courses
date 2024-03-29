package com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.lessons.part18mvvm.login.data

import com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.lessons.part18mvvm.login.data.network.LoginService

class LoginRepository {

    private val api = LoginService()

    suspend fun doLogin(user: String, password: String): Boolean =
        api.doLogin(user, password)

}