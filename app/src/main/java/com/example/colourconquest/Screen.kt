package com.example.colourconquest

sealed class Screen(val route: String) {
    object openingPage : Screen(route = "openingPage")
    object playerInfoPage : Screen(route = "playerInfoPage")
    object gameMode : Screen(route = "gameMode")
    object gamePage: Screen("gamePage")

}