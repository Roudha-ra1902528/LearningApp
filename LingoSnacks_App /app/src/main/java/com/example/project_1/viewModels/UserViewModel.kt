package com.example.project_1.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.project_1.model.User
import com.example.project_1.repo.UserRepo

class UserViewModel : ViewModel() {


    //user list new entered
    val users = UserRepo.users.toMutableStateList()

    var currentUser : MutableState<User?> = mutableStateOf(null)
    var bottomSheet : MutableState<Boolean> = mutableStateOf(false)

    fun isLogedIn() = currentUser.value != null

    var newUser : MutableState<User?> = mutableStateOf(null)

    var firstName = mutableStateOf("")
    var lastName = mutableStateOf("")
    var email = mutableStateOf("")
    var password = mutableStateOf("")
    var photoUrl = mutableStateOf("")

    var errorMsg = mutableStateOf("")

    //new user created
    fun signUpUser(): Boolean {
        newUser.value = User(firstName.value,lastName.value,email.value,password.value,photoUrl.value)

        newUser.value?.let {
            if (validateSignUp()){
                users.add(it)
                currentUser.value = newUser.value
                return true
            }else{
                return false
            }

        }

        return false
    }

    fun validateSignUp (): Boolean {
        if (firstName.value.isEmpty()) {

            errorMsg.value = "You need to enter your firstName"

            return false
        }
        if (lastName.value.isEmpty()) {

            errorMsg.value = "You need to enter your lastName"

            return false
        }
        if (email.value.isEmpty()) {

            errorMsg.value = "You need to enter your Email"


            return false
        }
        if (password.value.isEmpty()) {

            errorMsg.value = "You need to enter your Password"

            return false
        }
        if (users.find { it.email == email.value } != null) {

            errorMsg.value = "This email is not avaliable"

            return false
        }




        return true
    }

    //log in
    fun login(): Boolean {

        if (validateLoginIn()) {
            val u =
                users.find { it.email.equals(email.value, true) && it.password == password.value }
            if (u != null) {
                currentUser.value = u
                return true
            } else {
                errorMsg.value = "Your email or password is invalid, try again"
                return false
            }
        }else{
            return false
        }


    }


    fun validateLoginIn (): Boolean {
        if (email.value.isEmpty()) {

            errorMsg.value = "You need to enter your Email"


            return false
        }
        if (password.value.isEmpty()) {

            errorMsg.value = "You need to enter your Password"

            return false
        }

        return true
    }

}