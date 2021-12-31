package com.example.samuelectionapp.fragments.contestants


data class ContestantsObject(
    val contestantsName: String = " ",
    val contestantsImageUrl: String = " ",
    val contestantsEmail: String = " ",
    val contestantsRegNumber: String = " ",
    val contestantsSchool: String = " ",
    val contestantsPosition: String = " ",
    val contestantsCounter: Int = 0,
    //val schoolObject: HashMap<String, String>? = null,
    val schoolArray: ArrayList<String>? = null
)


//
//val contestantsName: String? = null,
//val contestantsImageUrl: String? = null,
//val contestantsEmail: String? = null,
//val contestantsRegNumber: String? = null,
//val contestantsSchool: String? = null,
//val contestantsPosition: String? = null,
//val contestantsCounter: Int? = null,