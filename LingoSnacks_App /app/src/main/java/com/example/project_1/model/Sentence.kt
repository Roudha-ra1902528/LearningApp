package com.example.project_1.model

//Done

import kotlinx.serialization.Serializable

@Serializable
data class Sentence (
     val text : String,
     val resources : List<Resource>
)