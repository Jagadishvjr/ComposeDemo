package com.example.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.utils.Action
import com.example.utils.Constants.LIST_ARG_KEY
import com.example.utils.Constants.PROPOSAL_DETAILS_ARG_KEY
import com.example.utils.Constants.SCREEN_LIST
import com.example.utils.Constants.SCREEN_PROPOSAL_DETAILS

fun NavGraphBuilder.proposalDetailComposable(
    navigateToListScreen : (Action) -> Unit
){
    composable(
        route = SCREEN_PROPOSAL_DETAILS,
        arguments = listOf(navArgument(PROPOSAL_DETAILS_ARG_KEY){
            type = NavType.IntType
        })
    ){

    }
}