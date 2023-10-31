package com.example.project_1.repo

data class PackageRatings (
    val packageId : Int,
    val ratings: MutableList<Rating>
)

data class Rating (
    val email : String,
    val comment : String,
    val doneAt : String,
    val rating : Float,
)

object RatingRepo {

    var packageRatings = mutableListOf<PackageRatings>() //*

    init {
        for (packageId in 1..4) {
            val ratings = mutableListOf(
                Rating("user1@example.com", "Good package", "27 Oct 2023 11:50", (1..500).random() / 100.0f),
                Rating("user2@example.com", "Overall good", "27 Oct 2023 11:51", (1..500).random() / 100.0f),
            )
            packageRatings.add(PackageRatings(packageId, ratings))
        }
    }

    fun addRating(packageId: Int, rating: Rating) {
        val packageRatingsEntry = packageRatings.find { it.packageId == packageId }

        if (packageRatingsEntry != null) {
            val userRatingIndex = packageRatingsEntry.ratings.indexOfFirst { it.email == rating.email }

            if (userRatingIndex != -1) {
                // User's rating already exists, so update it
                packageRatingsEntry.ratings[userRatingIndex] = rating
            } else {
                // User's rating doesn't exist, so add it
                packageRatingsEntry.ratings.add(rating)
            }
        } else {
            // Package entry doesn't exist, create a new one
            val newPackageRatings = PackageRatings(packageId, mutableListOf(rating))
            packageRatings.add(newPackageRatings)
        }
    }


    fun findUserRating(packageId: Int, userEmail: String): Rating? {
        val packageRatingsEntry = packageRatings.find { it.packageId == packageId }
        return packageRatingsEntry?.ratings?.find { it.email == userEmail }
    }

    fun averageRating(packageId: Int): Float {
        val packageRatingsEntry = packageRatings.find { it.packageId == packageId }

        val average = packageRatingsEntry?.ratings?.map { it.rating }?.average()?.toFloat() ?: 0.0f

        return String.format("%.1f", average).toFloat()
    }
}


// exeute each diff related to the word
//fun filterWords(query: Definition) = packages_Words.filter {
////    it.definition.contains(query)
//    it
//}

//
//fun addRating(packageId: Int, rating: Rating) {
//    val packageRatingsEntry = RatingRepo.packageRatings.find { it.packageId == packageId }
//
//    if (packageRatingsEntry != null) {
//        packageRatingsEntry.ratings.add(rating)
//    } else {
//        val newPackageRatings = PackageRatings(packageId, mutableListOf(rating))
//        RatingRepo.packageRatings.add(newPackageRatings)
//    }
//}