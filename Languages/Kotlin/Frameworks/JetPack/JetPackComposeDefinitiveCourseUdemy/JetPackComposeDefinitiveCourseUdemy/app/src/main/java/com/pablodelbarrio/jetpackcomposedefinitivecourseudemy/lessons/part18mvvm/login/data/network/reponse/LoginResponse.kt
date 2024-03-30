package com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.lessons.part18mvvm.login.data.network.reponse

import com.google.gson.annotations.SerializedName

data class LoginResponse(@SerializedName("success") val result: Boolean)
