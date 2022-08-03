package com.example.composedemo.composables

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FormDropDown(){
    var expanded by rememberSaveable { mutableStateOf(false) }

    Box(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.TopStart)) {
        IconButton(onClick = { expanded = true }) {
            Icon(Icons.Default.MoreVert, contentDescription = "Localized description")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(onClick = { /* Handle refresh! */ }) {
                Text("Refresh")
            }
            DropdownMenuItem(onClick = { /* Handle settings! */ }) {
                Text("Settings")
            }
            Divider()
            DropdownMenuItem(onClick = { /* Handle send feedback! */ }) {
                Text("Send Feedback")
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FormExposedDropDown(label : String ="label",options : List<String>){
    var expanded by rememberSaveable { mutableStateOf(false) }
    var selectedOptionText by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.size(1.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
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
                    onValueChange = { selectedOptionText = it },
                    label = { Text(label) },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expanded
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next)
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


@Preview
@Composable
private fun DefaultDropDownPreview(){
    FormExposedDropDown(options= listOf())
}

@Composable
fun ColumnScope.ProposalDrpDownBody(weight : Float, color : Color = MaterialTheme.colors.background){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .weight(weight),
        color = color
    ) {
        FormDropDownUI()
    }
}


@Composable
fun ColumnScope.ProposalDropDownFoot(weight : Float, color : Color = MaterialTheme.colors.background){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .weight(weight),
        color = color
    ) {
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
                Toast.makeText(context, "Uploading data ...", Toast.LENGTH_SHORT).show()
                Toast.makeText(context, "Uploaded", Toast.LENGTH_LONG).show()
            }) {
            Text(
                text = "Upload",
                fontSize = 30.sp
            )
        }
    }
}


@Composable
fun FormDropDownUI(
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(1.dp)
                .border(width = 1.dp, color = Color.Blue)
                .padding(1.dp)
        ) {
            val company = listOf("ICICI", "HDFC", "AXIS", "HSBC", "TATA Finance","Bajaj Auto","Reliance","Manapuram")
            FormExposedDropDown("Company",company)
            FormEditText("Name", "CustomerName")
            val division = listOf("Car", "Tractor", "Auto", "Bus", "Harvester","Jcb","Two wheeler","Heavy vehicle")
            FormExposedDropDown("Division",division)
            val location = listOf("Allahabad", "ArunachalPradesh", "Araku", "Pune", "Thane","Delhi","Goa","Hyderabad")
            FormExposedDropDown("Location",location)
            FormEditText("Total Due", "656200")
            val pin = listOf("400401", "400402", "400403","400404","400405","400406","400407","400408","400409","400410","400411","400412",
                "500401", "500402", "500403","500404","500405","500406","500407","500408","500409","500410","500411","500412",
                "600401", "600402", "600403","600404","600405","600406","600407","600408","600409","600410","600411","600412")
            FormExposedDropDown("Pin",pin)
            FormEditText("Email", "abc@gmail.com")
            val feildneOtions = listOf("Option1", "Option2","Option3","Option4","Option5",
                "Option6", "Option7","Option8","Option9","Option10")
            FormExposedDropDown("feildneOtions",feildneOtions)
            val field2Optons = listOf("Option1", "Option2","Option3","Option4","Option5",
                "Option6", "Option7","Option8","Option9","Option10")
            FormExposedDropDown("field2Optons",field2Optons)
            FormEditText("Total Due", "656200")
            val field3Optons = listOf("Option1", "Option2","Option3","Option4","Option5",
                "Option6", "Option7","Option8","Option9","Option10")
            FormExposedDropDown("field3Optons",field3Optons)
            val field4Optons = listOf("Option1", "Option2","Option3","Option4","Option5",
                "Option6", "Option7","Option8","Option9","Option10")
            FormExposedDropDown("field3Optons",field4Optons)
            val field5Optons = listOf("Option1", "Option2","Option3","Option4","Option5",
                "Option6", "Option7","Option8","Option9","Option10")
            FormExposedDropDown("field3Optons",field5Optons)
            FormEditText("Total Due", "656200")
            val field6Optons = listOf("Option1", "Option2","Option3","Option4","Option5",
                "Option6", "Option7","Option8","Option9","Option10")
            FormExposedDropDown("field3Optons",field6Optons)
            val field7Optons = listOf("Option1", "Option2","Option3","Option4","Option5",
                "Option6", "Option7","Option8","Option9","Option10")
            FormExposedDropDown("field3Optons",field7Optons)
            FormEditText("Total Due", "656200")
            val field8Optons = listOf("Option1", "Option2","Option3","Option4","Option5",
                "Option6", "Option7","Option8","Option9","Option10")
            FormExposedDropDown("field3Optons",field8Optons)
            val field9Optons = listOf("Option1", "Option2","Option3","Option4","Option5",
                "Option6", "Option7","Option8","Option9","Option10")
            FormExposedDropDown("field3Optons",field9Optons)
            }
        }
}


@Composable
fun ProposalDropDownFieldsFinal(){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        FormHeader(weight = 1f,MaterialTheme.colors.primaryVariant)
        ProposalDrpDownBody(weight = 8f)
        ProposalDropDownFoot(weight = 1f)
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview(){
    //FormDropDown()
    FormExposedDropDown("Label",listOf(""))
}