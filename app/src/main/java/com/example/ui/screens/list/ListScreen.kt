package com.example.ui.screens.list

import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.data.models.Proposal
import com.example.ui.viewmodels.ProposalViewModel

@Composable
fun ListScreen(
    proposalViewModel: ProposalViewModel,
    navigateToProposalScreen : (Int) -> Unit
){
    LaunchedEffect(
       key1 = true
    ){
        Log.d("ListScreen","LaunchedEffect Triggered")
        proposalViewModel.getAllProposals()
    }

    val proposals by proposalViewModel.allProposals.collectAsState()

    Scaffold(
        topBar = {
            ListAppBar()
        },
        content = {
            ListContent(
                proposals = proposals,
                navigateToProposalScreen = navigateToProposalScreen
            )
        }
    )
}


@Preview
@Composable
private fun DefaultPreview(){
//    ListScreen(
//        proposal = ,
//        navigateToProposalScreen = {}
//    )
}