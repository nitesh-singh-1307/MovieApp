package com.example.bookapplication.ui.navigation

import com.example.bookapplication.utils.BaseUrl.MOVIE_ID

sealed class Route{
    data class HomeScreen(val route: String = "HomeScreen"): Route()
    data class FileScreen(val route: String = "FileScreen",
        val routeWithArgument: String = "$route/{$MOVIE_ID}"
        ): Route(){

        fun passArgument(id: Int) = "$route/$id"
    }


}