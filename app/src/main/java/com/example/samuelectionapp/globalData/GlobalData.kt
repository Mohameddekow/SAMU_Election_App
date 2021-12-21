package com.example.samuelectionapp.globalData

import com.example.samuelectionapp.R
import com.example.samuelectionapp.fragments.contestants.ContestantsItems
import com.example.samuelectionapp.fragments.results.ChooseSchoolItems
import com.example.samuelectionapp.home.HomeItems



object GlobalData {


    val HomeData = listOf<HomeItems>(
        HomeItems(R.drawable.ic_contestants_24, "Contestants"),
        HomeItems(R.drawable.ic_vote_24, "Vote"),
        HomeItems(R.drawable.ic_result_24, "Results"),
        HomeItems(R.drawable.ic_help_24, "Help")
    )

    val chooseSchool = listOf<ChooseSchoolItems>(
        ChooseSchoolItems(R.drawable.agriculture, "School of Agriculture"),
        ChooseSchoolItems(R.drawable.business, "School of Business and Economics"),
        ChooseSchoolItems(R.drawable.computer_science, "School of Computing"),
        ChooseSchoolItems(R.drawable.engineering, "School of Engineering"),
        ChooseSchoolItems(R.drawable.school_of_health, "School of Health Science"),
        ChooseSchoolItems(R.drawable.maths, "School of Pure Maths")
    )

    //damy data
    val ContData = listOf<ContestantsItems>(
        ContestantsItems(R.drawable.ic_contestants_24, "Moha", "computing", "men's rep"),
        ContestantsItems(R.drawable.ic_help_24, "Ama", "computing", "chair"),
        ContestantsItems(R.drawable.vote_app_icon, "ana", "engi", "women's rep"),
        ContestantsItems(R.drawable.vote_app_icon, "ana", "engi", "women's rep"),
        ContestantsItems(R.drawable.vote_app_icon, "ana", "engi", "women's rep"),
        ContestantsItems(R.drawable.vote_app_icon, "ana", "engi", "women's rep"),
        ContestantsItems(R.drawable.vote_app_icon, "ana", "engi", "women's rep"),
        ContestantsItems(R.drawable.vote_app_icon, "ana", "engi", "women's rep"),
        ContestantsItems(R.drawable.vote_app_icon, "ana", "engi", "women's rep"),
        ContestantsItems(R.drawable.vote_app_icon, "ana", "engi", "women's rep"),
        ContestantsItems(R.drawable.vote_app_icon, "ana", "engi", "women's rep"),
        ContestantsItems(R.drawable.ic_vote_24, "Moha", "computing", "men's rep"),
        ContestantsItems(R.drawable.ic_contestants_24, "Moha", "computing", "men's rep"),
        ContestantsItems(R.drawable.ic_contestants_24, "Moha", "computing", "men's rep"),

    )


}