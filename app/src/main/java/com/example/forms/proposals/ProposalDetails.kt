package com.example.forms.proposals

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.formbuilder.*
import com.example.formbuilder.composables.alertdialogs.SimpleAlertDialog
import com.example.formbuilder.composables.datepicker.OutlinedDateField
import com.example.formbuilder.composables.datepicker.remeberOutlinedDateFieldState
import com.example.formbuilder.composables.dropdowns.outline.FormOutlinedDropDown
import com.example.formbuilder.composables.textfields.FormOutLinedTextInputField
import com.example.forms.proposals.Constants.FIELD_NAME_CUSTOMER_AGE
import com.example.forms.proposals.Constants.FIELD_NAME_CUSTOMER_COMPANY
import com.example.forms.proposals.Constants.FIELD_NAME_CUSTOMER_DIVISION
import com.example.forms.proposals.Constants.FIELD_NAME_CUSTOMER_DOB
import com.example.forms.proposals.Constants.FIELD_NAME_CUSTOMER_EMAIL
import com.example.forms.proposals.Constants.FIELD_NAME_CUSTOMER_LOCATION
import com.example.forms.proposals.Constants.FIELD_NAME_CUSTOMER_NAME
import com.example.forms.proposals.Constants.FIELD_NAME_CUSTOMER_PHONE
import com.example.forms.proposals.Constants.FIELD_NAME_CUSTOMER_PIN
import com.example.forms.proposals.Constants.FIELD_NAME_CUSTOMER_PROPOSAL_NO
import com.example.forms.proposals.Constants.FIELD_NAME_CUSTOMER_TOTAL_DUE

@Composable
fun ColumnScope.FormHeader(weight : Float,color : Color = MaterialTheme.colors.background){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .weight(weight)
            .align(Alignment.CenterHorizontally),
        color = color
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Proposal details",
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 35.sp
            )
        }

    }
}


@Composable
fun ProposalFormBodyUI(
    viewModel : ProposalDetailsViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        val formState: FormState<BaseState<out Any>> = remember { viewModel.formState }

        val proposalNoState: TextFieldState = formState.getState(FIELD_NAME_CUSTOMER_PROPOSAL_NO)
        val totalDueState: TextFieldState = formState.getState(FIELD_NAME_CUSTOMER_TOTAL_DUE)
        val nameState: TextFieldState = formState.getState(FIELD_NAME_CUSTOMER_NAME)
        val phoneState: TextFieldState = formState.getState(FIELD_NAME_CUSTOMER_PHONE)
        val ageState: TextFieldState = formState.getState(FIELD_NAME_CUSTOMER_AGE)
        val emailState: TextFieldState = formState.getState(FIELD_NAME_CUSTOMER_EMAIL)
        val outlinedDateFieldState = remeberOutlinedDateFieldState()
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
            FormOutLinedTextInputField("Proposal No",proposalNoState)

            FormDateFieldWith(state = formState.getState(FIELD_NAME_CUSTOMER_DOB))

            FormDropDownFieldWith(state = formState.getState(FIELD_NAME_CUSTOMER_COMPANY))
            FormDropDownFieldWith(state = formState.getState(FIELD_NAME_CUSTOMER_DIVISION))
            FormDropDownFieldWith(state = formState.getState(FIELD_NAME_CUSTOMER_LOCATION))
            FormOutLinedTextInputField("Customer Name",nameState)
            FormOutLinedTextInputField("Email",emailState, keyboardType = KeyboardType.Email)
            FormOutLinedTextInputField("Phone",phoneState,keyboardType = KeyboardType.Phone)
            FormOutLinedTextInputField("Age",ageState,keyboardType = KeyboardType.Number)
            FormOutLinedTextInputField("Total Due",totalDueState,keyboardType = KeyboardType.Number)
            FormDropDownFieldWith(state = formState.getState(FIELD_NAME_CUSTOMER_PIN))
        }
    }
}

@Composable
fun FormDropDownFieldWith(
    state : DropDownState ,
){
    // ----------->Start
    FormOutlinedDropDown(state)
    if (state.hasError) Text(state.errorMessage, color = Color.Red)
    Spacer(modifier = Modifier.height(20.dp))
    // ----------->End
}


@Composable
fun FormDateFieldWith(
    state : DateState
){
    // ----------->Start
    OutlinedDateField(state)
    if (state.hasError) Text(state.errorMessage, color = Color.Red)
    Spacer(modifier = Modifier.height(20.dp))
    // ----------->End
}

@Composable
fun ColumnScope.ProposalFormBody(viewModel : ProposalDetailsViewModel,weight : Float, color : Color = MaterialTheme.colors.background){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .weight(weight),
        color = color
    ) {
        ProposalFormBodyUI(viewModel)
    }
}


@Composable
fun ColumnScope.ProposalFormFooter(viewModel : ProposalDetailsViewModel,weight : Float, color : Color = MaterialTheme.colors.background){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .weight(weight),
        color = color
    ) {
         val showDialog = rememberSaveable {
             mutableStateOf(false)
         }
        var alertMsg = rememberSaveable {
            mutableStateOf("Please check the form data")
        }
        val context = LocalContext.current
        Button(
            modifier = Modifier
                .height(60.dp)
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 4.dp,
                    bottom = 4.dp
                ),
            onClick = {
                val isFormValid = viewModel.submit()
                showDialog.value=true
                if(isFormValid){
                    viewModel.updateFormAsReadOnly()
                    alertMsg.value = "Successfully uploaded"
                    //Toast.makeText(context, "Validations successful, Uploading data ...", Toast.LENGTH_SHORT).show()
                }else{
                    alertMsg.value = "Validations are Failed, Please check and fill all the fields"
                    //Toast.makeText(context, "Validations failed...", Toast.LENGTH_LONG).show()
                }
                //Toast.makeText(context, "Uploading data ...", Toast.LENGTH_SHORT).show()
                //Toast.makeText(context, "Uploaded", Toast.LENGTH_LONG).show()
            }) {
            Text(
                text = "Upload",
                fontSize = 30.sp
            )
        }
        if(showDialog.value){
            SimpleAlertDialog(
                message = alertMsg.value,
                onConfirmationClicked = {showDialog.value=false}
            )
        }

    }
}


@Composable
fun ProposalFormScreen(viewModel : ProposalDetailsViewModel){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        FormHeader(weight = 1f,MaterialTheme.colors.primaryVariant)
        ProposalFormBody(viewModel,weight = 8f)
        ProposalFormFooter(viewModel,weight = 1f)
    }
}