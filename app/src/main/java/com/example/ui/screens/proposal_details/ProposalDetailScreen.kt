package com.example.ui.screens.proposal_details

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.formbuilder.TextFieldState
import com.example.formbuilder.composables.datepicker.remeberOutlinedDateFieldState
import com.example.formbuilder.composables.textfields.FormOutLinedTextInputField
import com.example.forms.proposals.Constants
import com.example.forms.proposals.FormDateFieldWith
import com.example.forms.proposals.FormDropDownFieldWith
import com.example.ui.viewmodels.ProposalDetailsScreenViewModel

@Composable
fun PropDetailScreen(
    selectedProposalId : Int,
    viewModel : ProposalDetailsScreenViewModel
){
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primary,
                title = { Text(text = "Proposal Details") }
            )
        }
    ) {
        ProposalFormBodyUI(selectedProposalId,viewModel)
    }
}

@Composable
fun ProposalFormBodyUI(
    selectedProposalId : Int,
    viewModel : ProposalDetailsScreenViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        LaunchedEffect(
            key1 = true
        ) {
            viewModel.getSelectedFormState(selectedProposalId)
        }
        val fmSt by viewModel.selectedFormState.collectAsState()
        //val formState: FormState<BaseState<out Any>> = remember { viewModel.formState }
        val scrollState = rememberScrollState()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(1.dp)
                .border(width = 1.dp, color = Color.Blue)
                .padding(1.dp)
        ) {
            Spacer(modifier = Modifier.height(15.dp))
            FormOutLinedTextInputField("Proposal No",fmSt.getState(Constants.FIELD_NAME_CUSTOMER_PROPOSAL_NO))
            FormDateFieldWith(state = fmSt.getState(Constants.FIELD_NAME_CUSTOMER_DOB))
            FormDropDownFieldWith(state = fmSt.getState(Constants.FIELD_NAME_CUSTOMER_COMPANY))
            FormDropDownFieldWith(state = fmSt.getState(Constants.FIELD_NAME_CUSTOMER_DIVISION))
            FormDropDownFieldWith(state = fmSt.getState(Constants.FIELD_NAME_CUSTOMER_LOCATION))
            FormOutLinedTextInputField("Customer Name",fmSt.getState(Constants.FIELD_NAME_CUSTOMER_NAME))
            FormOutLinedTextInputField("Email",fmSt.getState(Constants.FIELD_NAME_CUSTOMER_EMAIL), keyboardType = KeyboardType.Email)
            FormOutLinedTextInputField("Phone",fmSt.getState(Constants.FIELD_NAME_CUSTOMER_PHONE),keyboardType = KeyboardType.Phone)
            FormOutLinedTextInputField("Age",fmSt.getState(Constants.FIELD_NAME_CUSTOMER_AGE),keyboardType = KeyboardType.Number)
            FormOutLinedTextInputField("Total Due",fmSt.getState(Constants.FIELD_NAME_CUSTOMER_TOTAL_DUE),keyboardType = KeyboardType.Number)
            FormDropDownFieldWith(state = fmSt.getState(Constants.FIELD_NAME_CUSTOMER_PIN))
        }
    }
}