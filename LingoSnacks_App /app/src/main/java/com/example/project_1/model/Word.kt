package com.example.project_1.model

//Done
import kotlinx.serialization.Serializable

@Serializable

data class Word(

     val text: String,
     val definitions: List<Definition>,
     val sentences: List<Sentence>,
     val resources: List<Resource> = emptyList()

)


