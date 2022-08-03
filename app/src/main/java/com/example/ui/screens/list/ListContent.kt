package com.example.ui.screens.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import com.example.data.models.Proposal
import com.example.ui.theme.LARGE_PADDING
import com.example.ui.theme.TASK_ITEM_ELEVATION
import com.example.ui.theme.taskItemBackgroundColor
import com.example.ui.theme.taskItemTextColor

@Composable
fun ListContent(
    proposals: List<Proposal>,
    navigateToProposalScreen : (id:Int) -> Unit
){
    LazyColumn {
        items(
            items = proposals,
            key =  { proposal ->
                proposal.id
            }
        ){ proposal ->
            ProposalItem(
                proposal = proposal,
                navigateToProposalScreen = navigateToProposalScreen
            )
        }
    }

}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProposalItem(
    proposal: Proposal,
    navigateToProposalScreen : (id:Int) -> Unit
){

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.taskItemBackgroundColor,
        shape = RectangleShape,
        elevation = TASK_ITEM_ELEVATION,
        onClick = {
            navigateToProposalScreen(proposal.id)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(all = LARGE_PADDING)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = proposal.proposal_no,
                color = MaterialTheme.colors.taskItemTextColor,
                style = MaterialTheme.typography.h5,
                maxLines = 1
            )

            Text(
                text = proposal.customer_name,
                color = MaterialTheme.colors.taskItemTextColor,
                style = MaterialTheme.typography.h5,
                maxLines = 1
            )
        }

    }

}


@Preview(showBackground = true)
@Composable
private fun DefaultPreview(){
    ProposalItem(
       proposal =  Proposal(
            id = 0,
            proposal_no = "",
            dob = "",
            company = "",
           division = "",
           location = "",
           customer_name = "",
           age = 12,
           mobile_no = "",
           email = "",
           pin = 5093444,
           total_due = 20.0
        ),
        navigateToProposalScreen = {}
    )
}