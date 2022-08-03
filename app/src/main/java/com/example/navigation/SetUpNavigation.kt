package com.example.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.navigation.destinations.listComposable
import com.example.navigation.destinations.proposalDetailComposable
import com.example.ui.viewmodels.ProposalViewModel
import com.example.utils.Constants.SCREEN_LIST

@Composable
fun SetUpNavigation(
    proposalViewModel: ProposalViewModel,
    navController: NavHostController
){

    val screen = remember(navController){
        Screens(navController = navController)
    }

    NavHost(
        navController=navController,
        startDestination = SCREEN_LIST
    ){
        listComposable(
            proposalViewModel = proposalViewModel,
            navigateToProposalDetailsScreen = screen.proposal_detail
        )
        proposalDetailComposable(
          navigateToListScreen = screen.list
        )
    }

}