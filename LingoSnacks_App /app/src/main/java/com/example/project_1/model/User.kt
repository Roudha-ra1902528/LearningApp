package com.example.project_1.model

import kotlinx.serialization.Serializable

@Serializable
data class User (
//     val id : String = "",
     val firstName : String = "",
     val lastName : String = "",
     val email : String = "",
     val password : String = "",
     val photoUri : String = "",
     val role : String = "Student"
)