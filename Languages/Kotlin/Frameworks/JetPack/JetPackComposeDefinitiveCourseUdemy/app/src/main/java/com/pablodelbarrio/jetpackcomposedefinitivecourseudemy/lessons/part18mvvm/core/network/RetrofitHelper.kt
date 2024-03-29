package com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.lessons.part18mvvm.core.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    fun getRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("http://run.mocky.io./")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}