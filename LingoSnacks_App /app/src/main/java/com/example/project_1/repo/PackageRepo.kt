package com.example.project_1.repo

import android.content.Context
import com.example.project_1.model.Definition
import com.example.project_1.model.LearningPackage
import com.example.project_1.model.Word
import kotlinx.serialization.json.Json


// verify the  password --> as parameter receive email &  pass



    // create the user account , email ( check if condition not duplicatied + ends with @test.com)


    //store the rate


    // game --> timer + levels (which) + rflect the result


// Get the words with definition from the json
object PackageRepo {

    var learningPackages : MutableList<LearningPackage>  = mutableListOf() //*

    fun initPackage(context: Context) {

        if(learningPackages.isEmpty()) {
            val jsonText = context.assets.open("packages.json").bufferedReader().use { it.readText() }
            learningPackages = Json { ignoreUnknownKeys = true }.decodeFromString(jsonText)
        }

    }

    fun getPackagesFromJson(context: Context): List<LearningPackage> { //* read user json -> login
        if (learningPackages.isEmpty()) {
            //        text content of the file can be read as
            val packages_WordsJsonText = context
                .assets
                .open("packages.json")
                .bufferedReader()
                .use {
                    it.readText()
                }
//        convert the json text to a list of stadiums
            learningPackages = Json { ignoreUnknownKeys = true }
                .decodeFromString(packages_WordsJsonText)
        }

        return learningPackages
    }

    fun getPackage(packageId: Int) = learningPackages.find { it.packageId == packageId }!!

    fun getSentences(packageId: Int) = learningPackages.find { it.packageId == packageId }?.words
        ?.flatMap { it.sentences }
        ?.map { it.text } ?: listOf("")
}


// exeute each diff related to the word
//fun filterWords(query: Definition) = packages_Words.filter {
////    it.definition.contains(query)
//    it
//}


