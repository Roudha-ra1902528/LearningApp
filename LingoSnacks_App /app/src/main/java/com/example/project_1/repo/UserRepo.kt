package com.example.project_1.repo

import android.content.Context
import com.example.project_1.model.LearningPackage
import com.example.project_1.model.User
import kotlinx.serialization.json.Json


// Get the users from the json
object UserRepo {

    var users : MutableList<User>  = mutableListOf()


    fun getUsersFromJson(context: Context): List<User> {
        if (users.isEmpty()) {
            //        text content of the file can be read as
            val usersJsonText = context
                .assets
                .open("users.json")
                .bufferedReader()
                .use {
                    it.readText()
                }
//        convert the json text to a list of users
            users = Json { ignoreUnknownKeys = true }
                .decodeFromString(usersJsonText)
        }

        return users
    }
}


// verify the  password --> as parameter receive email &  pass



// create the user account , email ( check if condition not duplicatied + ends with @test.com)
//fun add(){
//
//}

//store the rate


// game --> timer + levels (which) + rflect the result