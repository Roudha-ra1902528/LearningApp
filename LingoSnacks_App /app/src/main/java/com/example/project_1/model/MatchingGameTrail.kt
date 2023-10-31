package com.example.project_1.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


data class MatchingGameTrail (
   val word: Word,
    val definitionAnswers:List<Definition>,
    val correctDefinition: Definition,
    val intialSelection :Int = -1

){
    var currentSelection by mutableStateOf(intialSelection)

    fun isCorrect() = currentSelection != intialSelection && definitionAnswers[currentSelection] == correctDefinition

}