package com.example.forms.proposals

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.ViewModel
import com.example.composedemo.R
import com.example.formbuilder.*
import com.example.forms.proposals.Constants.FIELD_NAME_CUSTOMER_NAME
import com.example.forms.proposals.Constants.FIELD_NAME_CUSTOMER_PROPOSAL_NO


@Composable
fun CustomDropDown(state : DropDownState){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(62.dp)
            .padding(
                start = 5.dp,
                end = 5.dp
            )
    ) {
        Box() {
            OutlinedTextField(
                value =state.selectedVal ,
                onValueChange ={},//will not respond to keyboard
                label = {Text(text = state.name)},
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = if(state.enabled)
                            R.drawable.ic_dropdown_arrow_up
                        else
                            R.drawable.ic_dropdown_arrow_down
                        ),
                        contentDescription =null,
                        Modifier.clickable {
                            state.onEnabled(!(state.enabled))
                        }
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned {
                        state.onSize(it.size.toSize())
                    },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next)
            )
            DropdownMenu(
                expanded = state.enabled,
                onDismissRequest = {
                    state.onEnabled(false)
                },
                modifier = Modifier.width(with(LocalDensity.current){state.size.width.toDp()})
            ) {
                state.list.forEachIndexed{ index, s ->
                    DropdownMenuItem(onClick = {
                        state.onSelectedIndex(index)
                        state.onEnabled(false)
                    }) {
                        val isSelected = state.selectedVal == s
                        val style = if (isSelected) {
                            MaterialTheme.typography.body1.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colors.secondary
                            )
                        } else {
                            MaterialTheme.typography.body1.copy(
                                fontWeight = FontWeight.Normal,
                                color = MaterialTheme.colors.onSurface
                            )
                        }
                        Text(text = s, style = style )
                    }
                }
            }
        }
    }

}

@Composable
fun SelectGender(genderState: ChoiceState) {
    val radioGroupOptions = listOf(
        "Male",
        "Female",
        "Non Binary",
        "Prefer not to say"
    )
    val selected = genderState.value
    val onSelectedChanged = { text: String -> genderState.change(text) }
    Column(
        modifier = Modifier
            .selectableGroup()
    ) {
        radioGroupOptions.forEach {
            Row(modifier = Modifier.fillMaxWidth()) {
                RadioButton(
                    selected = selected == it,
                    onClick = { onSelectedChanged(it) },
                )
                Text(text = it, modifier = Modifier.padding(top = 14.dp))
            }
        }

    }
}

@Composable
fun HappinessIndex(happinessState: TextFieldState) {
    val value = if (happinessState.value == "") 0.0f else happinessState.value.toFloat()
    Text(text = "Happiness level: ${(value * 100).toInt()}")
    Slider(
        value = value,
        onValueChange = { happinessState.change(it.toString()) })
}

@Composable
fun Hobbies(hobbyState: SelectState) {
    val hobbiesList = listOf(
        "Chess",
        "Sky Diving",
        "Reading",
        "Travelling",
        "Bike Riding",
        "Mountain Climbing"
    )
    Text(text = "Hobbies")

    hobbiesList.forEach { hobby ->
        Row(Modifier.fillMaxWidth()) {
            Checkbox(
                checked = hobbyState.value.contains(hobby),
                onCheckedChange = {
                    if (it) hobbyState.select(hobby) else hobbyState.unselect(hobby)
                }
            )
            Text(text = hobby, modifier = Modifier.padding(top = 14.dp))
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FormExposedSelectionDropDown(state: ChoiceState){
    val options = listOf("ICICI", "HDFC", "AXIS", "HSBC", "TATA Finance","Bajaj Auto","Reliance","Manapuram")
    val label = state.name
    var expanded by rememberSaveable { mutableStateOf(false) }
    var selectedOptionText = state.value
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.size(1.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(62.dp)
                .padding(
                    start = 5.dp,
                    end = 5.dp
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.size(4.dp))
            ExposedDropdownMenuBox(
                modifier = Modifier.fillMaxWidth(),
                expanded = expanded,
                onExpandedChange = {
                    expanded = !expanded
                }
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = selectedOptionText,
                    onValueChange = { newText ->
                        state.value = newText
                    },
                    label = { Text(label) },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expanded
                        )
                    }
                )
                // filter options based on text field value
                val filteringOptions =
                    options.filter { it.startsWith(selectedOptionText, ignoreCase = true) }
                if (filteringOptions.isNotEmpty()) {
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = {
                            expanded = false
                        }
                    ) {
                        filteringOptions.forEach { selectionOption ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedOptionText = selectionOption
                                    expanded = false
                                }
                            ) {
                                Text(text = selectionOption)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Content(viewModel : ProposalDetailsViewModel) {
    val formState: FormState<BaseState<out Any>> = remember { viewModel.formState }
    val ageState: TextFieldState = formState.getState("age")
    //val genderState: ChoiceState = formState.getState("gender")
    val emailState: TextFieldState = formState.getState("email")
    val passwordState: TextFieldState = formState.getState("password")
    //val happinessState: TextFieldState = formState.getState("happiness")
    //val hobbiesState: SelectState = formState.getState<SelectState>("hobbies")

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {

        OutlinedTextField(
            value = emailState.value,
            isError = emailState.hasError,
            label = { Text("Email address") },
            onValueChange = { emailState.change(it) }
        )

        if (emailState.hasError) Text(emailState.errorMessage, color = Color.Red)

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = passwordState.value,
            isError = passwordState.hasError,
            label = { Text("Password") },
            onValueChange = { passwordState.change(it) }
        )

        if (passwordState.hasError) Text(passwordState.errorMessage, color = Color.Red)

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = ageState.value,
            isError = ageState.hasError,
            label = { Text("Age") },
            onValueChange = { ageState.change(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        if (ageState.hasError) Text(ageState.errorMessage, color = Color.Red)

        Spacer(modifier = Modifier.height(20.dp))

        //SelectGender(genderState = genderState)

        //if (genderState.hasError) Text(genderState.errorMessage, color = Color.Red)

        Spacer(modifier = Modifier.height(20.dp))
        // HappinessIndex(happinessState = happinessState)

        // if (happinessState.hasError) Text(happinessState.errorMessage, color = Color.Red)

        //Hobbies(hobbyState = hobbiesState)

        // if (hobbiesState.hasError) Text(hobbiesState.errorMessage, color = Color.Red)

        Button(
            modifier = Modifier.padding(16.dp),
            onClick = { viewModel.submit() },
            content = { Text("submit") },
        )
    }
}



@Composable
fun ContentV2(viewModel : ProposalDetailsViewModel) {
    val formState: FormState<BaseState<out Any>> = remember { viewModel.formState }

    val proposalNoState: TextFieldState = formState.getState(FIELD_NAME_CUSTOMER_PROPOSAL_NO)
    val nameState: TextFieldState = formState.getState(FIELD_NAME_CUSTOMER_NAME)
    val ageState: TextFieldState = formState.getState("age")
    val genderState: ChoiceState = formState.getState("gender")

    val emailState: TextFieldState = formState.getState("email")

    val passwordState: TextFieldState = formState.getState("password")
   // val happinessState: TextFieldState = formState.getState("happiness")
   // val hobbiesState: SelectState = formState.getState<SelectState>("hobbies")

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {

        Spacer(modifier = Modifier.height(30.dp))

        //Proposal No ----------->Start
        OutlinedTextField(
            value = proposalNoState.value,
            isError = proposalNoState.hasError,
            label = { Text("Proposal No") },
            onValueChange = { proposalNoState.change(it) }
        )

        if (proposalNoState.hasError) Text(proposalNoState.errorMessage, color = Color.Red)

        Spacer(modifier = Modifier.height(20.dp))
        //Proposal No ----------->End

        //Customer Name ----------->Start
        OutlinedTextField(
            value = nameState.value,
            isError = nameState.hasError,
            label = { Text("Customer Name") },
            onValueChange = { nameState.change(it) }
        )

        if (nameState.hasError) Text(nameState.errorMessage, color = Color.Red)

        Spacer(modifier = Modifier.height(20.dp))
        //Customer Name ----------->End

        OutlinedTextField(
            value = emailState.value,
            isError = emailState.hasError,
            label = { Text("Email address") },
            onValueChange = { emailState.change(it) }
        )

        if (emailState.hasError) Text(emailState.errorMessage, color = Color.Red)

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = passwordState.value,
            isError = passwordState.hasError,
            label = { Text("Password") },
            onValueChange = { passwordState.change(it) }
        )

        if (passwordState.hasError) Text(passwordState.errorMessage, color = Color.Red)

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = ageState.value,
            isError = ageState.hasError,
            label = { Text("Age") },
            onValueChange = { ageState.change(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        if (ageState.hasError) Text(ageState.errorMessage, color = Color.Red)

        Spacer(modifier = Modifier.height(20.dp))


        Button(
            modifier = Modifier.padding(16.dp),
            onClick = { viewModel.submit() },
            content = { Text("submit") },
        )
    }
}

