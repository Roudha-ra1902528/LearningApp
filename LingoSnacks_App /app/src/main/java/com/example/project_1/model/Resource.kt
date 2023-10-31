package com.example.project_1.model

//Done
import kotlinx.serialization.Serializable

@Serializable

data class Resource(
      val extension : String  = "",
      val resourceUrl : String = " ",
     val title : String = "",
     val type : ResourceTypeEnum = ResourceTypeEnum.Photo

)