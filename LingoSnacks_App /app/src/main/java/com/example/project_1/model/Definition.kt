package com.example.project_1.model

//Done
import kotlinx.serialization.Serializable

@Serializable
data class Definition (
     val text : String,
     val source : String
)