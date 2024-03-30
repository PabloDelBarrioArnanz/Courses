package com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.lessons.part18mvvm.login.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginService @Inject constructor(private val loginClient: LoginClient) {

    suspend fun doLogin(user: String, password: String): Boolean =
        withContext(Dispatchers.IO) {
            //retrofit.create(LoginClient::class.java).doLogin().body()?.result ?: false
            loginClient.doLogin().body()?.result ?: false
        }

}