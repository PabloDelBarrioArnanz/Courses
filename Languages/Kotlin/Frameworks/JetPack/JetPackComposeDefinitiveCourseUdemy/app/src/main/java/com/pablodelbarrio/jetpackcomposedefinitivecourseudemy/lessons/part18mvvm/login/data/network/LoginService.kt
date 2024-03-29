package com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.lessons.part18mvvm.login.data.network

import com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.lessons.part18mvvm.core.network.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun doLogin(user: String, password: String): Boolean =
        withContext(Dispatchers.IO) {
            retrofit.create(LoginClient::class.java).doLogin().body()?.result ?: false
        }

}