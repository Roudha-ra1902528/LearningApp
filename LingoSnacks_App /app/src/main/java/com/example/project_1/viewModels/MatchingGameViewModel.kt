package com.example.project_1.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.project_1.model.LearningPackage
import com.example.project_1.model.MatchingGameTrail
import com.example.project_1.repo.PackageRepo
import com.example.project_1.screens.MatchingWordGameTrial


class MatchingGameViewModel : ViewModel() {
    var packageId = mutableStateOf(0)
    private var selectedPackage: LearningPackage = PackageRepo.learningPackages[packageId.value]

    //        PackageRepo.learningPackages[packageId.value]// for the selected pacakage, assume 1 package
    var trials = getTrials(selectedPackage)


    var trialsLength = trials.size

//    val trialList = remember {  viewModel.trials  }

    private var _trialNumber = mutableStateOf(0)
    val trialNumber: MutableState<Int>
        get() = _trialNumber

    private var _trial = mutableStateOf(trials[trialNumber.value])
    val trial: MutableState<MatchingGameTrail>
        get() = _trial

    //    var intialSection by remember { mutableStateOf(-1) }
    private var _correctAnswer = mutableStateOf(0)

    val correctAnswer: MutableState<Int>
        get() = _correctAnswer


    private var _inCorrectAnswer = mutableStateOf(0)

    val inCorrectAnswer: MutableState<Int>
        get() = _inCorrectAnswer

    private var _showScore = mutableStateOf(false)

    val showScore: MutableState<Boolean>
        get() = _showScore

    private var _showTrial = mutableStateOf(true)
    val showTrial: MutableState<Boolean>
        get() = _showTrial

    private var _nextButtonEnabled = mutableStateOf(false)
    val nextButtonEnabled: MutableState<Boolean>
        get() = _nextButtonEnabled


    val counterText : String
        get() = "${trialNumber.value + 1}/${trialsLength}"
    //trialNumber.value < trials.size && trial.value.currentSelection != -1
//    var nextButtonEnabled : Boolean = false
//        get() = _nextButtonEnabled.value //trialNumber.value < trials.size && trial.value.currentSelection != -1 // we reach to the end of the list}

//    fun increaseIncorrect() = inCorrectAnswer.value++
//    fun increaseCorrect() = correctAnswer.value++
//    fun increaseTrialNumber() = trialNumber.value++
//    fun decreaseTrialNumber() = trialNumber.value--

    fun getNextTrial() {


        if (_trial.value.isCorrect()) {
            _correctAnswer.value++
        } else {
            _inCorrectAnswer.value++
        }

//        increaseTrialNumber()
        if (_trialNumber.value == trials.size - 1) {
            _showScore.value = true
            _nextButtonEnabled.value = false
            _showTrial.value = false
//            decreaseTrialNumber()
        } else {
            _trialNumber.value++
            _trial.value = trials[_trialNumber.value]
            _nextButtonEnabled.value = false

        }

    }


    //    fun isNextButtonEnabled (): Boolean {
//       return trialNumber.value < trials.size && trial.value.currentSelection != -1 // we reach to the end of the list}
//    }
    fun setPackageId(id: Int) {
        packageId.value = id
//        selectedPackage = PackageRepo.learningPackages[packageId.value]
        selectedPackage = PackageRepo.learningPackages.find { it.packageId == packageId.value }!!
        trials = getTrials(selectedPackage)
        trialsLength = trials.size
        _trial.value = trials[0]
    }

    fun enableNextButton() {
        nextButtonEnabled.value = true
    }

    fun setCurrentSelection(selection: Int) {
        trial.value.currentSelection = selection
    }


    private fun getTrials(selectedPackage: LearningPackage): List<MatchingGameTrail> {
        return selectedPackage.let { pk ->
            List(pk.words.size) {
                val word = pk.words[it]
                val correctDefintion = word.definitions.random()

                val inCorrectDefintions = pk.words.filter { it != word }// get other words
                    .flatMap { it.definitions } // the difinitons of other words
                    .shuffled()// scramble the definiton
                    .take(3)// incorrect defintions

                //incorrect + correct
                val answers = inCorrectDefintions.toMutableList()
                    .apply { add(correctDefintion) } // add-> T/F,apply -> scop fun -> as update list

                MatchingGameTrail(word, answers.shuffled().toList(), correctDefintion)
            }
        } //?: emptyList<MatchingGameTrail>()

    }

    fun resetGame() {
        setPackageId(packageId.value) // reset
        //- private in the class
        _trialNumber.value = 0
        _showTrial.value = true
        _showScore.value = false
        _correctAnswer.value = 0
        _inCorrectAnswer.value = 0

    }

}

