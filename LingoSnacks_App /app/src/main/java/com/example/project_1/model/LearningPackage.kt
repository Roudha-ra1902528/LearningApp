package com.example.project_1.model



//Done

import com.example.project_1.repo.Rating
import kotlinx.serialization.Serializable

@Serializable

data class LearningPackage (
     val packageId : Int,
     val author :String = "",
     val category : String  = "",
     val description : String  = "",
     val iconUrl : String  = "",
     val keyWords : List <String> = emptyList(), // for search not change on it else mutable
     val language : String  = "",
     val lastUpdatedDate : String  = "",
     val level : String  = "",
     val title : String  = "",
     val version : Int,
     val words : List<Word> ,
//     val ratings : MutableList<Rating>  = mutableListOf() //changable
)