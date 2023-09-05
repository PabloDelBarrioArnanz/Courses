package com.mouredevbackendapirestwithtktor.models

import kotlinx.serialization.Serializable

@Serializable // para transformarlo a json
data class User(val id: Int, val name: String, val age: Int, val email: String)
