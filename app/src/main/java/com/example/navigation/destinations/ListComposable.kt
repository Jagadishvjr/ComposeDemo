package com.example.navigation.destinations

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.data.models.Proposal
import com.example.ui.screens.list.ListScreen
import com.example.ui.viewmodels.ProposalViewModel
import com.example.utils.Constants.LIST_ARG_KEY
import com.example.utils.Constants.SCREEN_LIST

fun NavGraphBuilder.listComposable(
    proposalViewModel: ProposalViewModel,
    navigateToProposalDetailsScreen : (Int) -> Unit
){
    composable(
        route = SCREEN_LIST,
        arguments = listOf(navArgument(LIST_ARG_KEY){
            type = NavType.StringType
        })
    ){
        ListScreen(
            proposalViewModel = proposalViewModel,
            navigateToProposalScreen = navigateToProposalDetailsScreen
        )
    }
}