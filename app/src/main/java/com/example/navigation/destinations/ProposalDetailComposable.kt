package com.example.navigation.destinations

import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.ui.screens.proposal_details.PropDetailScreen
import com.example.ui.viewmodels.ProposalDetailsScreenViewModel
import com.example.ui.viewmodels.ProposalViewModel
import com.example.utils.Action
import com.example.utils.Constants.LIST_ARG_KEY
import com.example.utils.Constants.PROPOSAL_DETAILS_ARG_KEY
import com.example.utils.Constants.SCREEN_LIST
import com.example.utils.Constants.SCREEN_PROPOSAL_DETAILS

fun NavGraphBuilder.proposalDetailComposable(
    proposalDetailsViewModel: ProposalDetailsScreenViewModel,
    navigateToListScreen : (Action) -> Unit
){
    composable(
        route = SCREEN_PROPOSAL_DETAILS,
        arguments = listOf(navArgument(PROPOSAL_DETAILS_ARG_KEY){
            type = NavType.IntType
        })
    ){ selectedProposalId->
        Log.d("LISTSCREEN", "selectedProposalId: $selectedProposalId")
        PropDetailScreen(
            selectedProposalId = 1,
            viewModel = proposalDetailsViewModel
        )
    }
}