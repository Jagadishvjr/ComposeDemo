package com.example.navigation

import androidx.navigation.NavHostController
import com.example.utils.Action
import com.example.utils.Constants.SCREEN_LIST

class Screens(navController : NavHostController) {

    val list : (Action) -> Unit = { action ->
        navController.navigate("list/${action.name}"){
            popUpTo(SCREEN_LIST){inclusive = true}
        }
    }

    val proposal_detail : (Int) -> Unit = { id ->
        navController.navigate("proposal_details/$id")
    }

}