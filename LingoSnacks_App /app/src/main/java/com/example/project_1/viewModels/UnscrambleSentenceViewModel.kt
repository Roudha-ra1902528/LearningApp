package com.example.project_1.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.project_1.model.LearningPackage
import com.example.project_1.repo.PackageRepo

class UnscrambleSentenceViewModel : ViewModel() {
    var scores = mutableStateListOf(0, 0, 0, 0, 0)

    fun trackScores(status: String, index: Int) {
        if (status == "success" && index in 0..4)
            scores[index] += 1
    }

}