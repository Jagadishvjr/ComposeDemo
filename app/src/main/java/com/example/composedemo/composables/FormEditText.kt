package com.example.composedemo.composables

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FormEditText(
    fieldName : String,
    fieldValue: String = ""
){
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.size(1.dp))
        Row (
            modifier = Modifier.fillMaxWidth()
                ){
            var enteredVal  by rememberSaveable { mutableStateOf("") }
            if(fieldValue.isNotEmpty()) enteredVal = fieldValue
            EditTextField(
                fieldValue = fieldValue,
                onValueChanged = {
                    enteredVal = it
                },
                fieldLabel = fieldName
            )
        }
        Spacer(modifier = Modifier.size(1.dp))
    }
}

@Composable
fun EditTextField(
    fieldValue : String,
    fieldLabel : String,
    onValueChanged : (String) -> Unit
){
    TextField(
        value = fieldValue,
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colors.primaryVariant
        ),
        label = {
            Text(
                text = fieldLabel,
                color = MaterialTheme.colors.onSecondary
            )},
        onValueChange =onValueChanged,
        modifier = Modifier
            .fillMaxWidth()
            .width(60.dp)
    )
}


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
fun ColumnScope.FormBody(weight : Float,color : Color = MaterialTheme.colors.background){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .weight(weight),
        color = color
    ) {
        ProposalFormFields()
    }
}


@Composable
fun ColumnScope.FormFooter(weight : Float,color : Color = MaterialTheme.colors.background){
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
fun ProposalFormFields(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
            .border(width = 1.dp, color = Color.Blue)
            .padding(2.dp)
            .verticalScroll(rememberScrollState())
    ) {

        val pin = listOf("400401", "400402", "400403","400404","400405","400406","400407","400408","400409","400410","400411","400412",
            "500401", "500402", "500403","500404","500405","500406","500407","500408","500409","500410","500411","500412",
            "600401", "600402", "600403","600404","600405","600406","600407","600408","600409","600410","600411","600412")

        val feildneOtions = listOf("Option1", "Option2","Option3","Option4","Option5",
            "Option6", "Option7","Option8","Option9","Option10")

        FormEditText("Proposal No", "PG/040/A/121/2002")

        val division = listOf("Car", "Tractor", "Auto", "Bus", "Harvester","Jcb","Two wheeler","Heavy vehicle")
        FormExposedDropDown("Division",division)
//        FormEditText("Division Name", "CAR")

//        FormEditText("Company", "ICICI")
        val company = listOf("ICICI", "HDFC", "AXIS", "HSBC", "TATA Finance","Bajaj Auto","Reliance","Manapuram")
        FormExposedDropDown("Company",company)

        FormEditText("Party name", "Ram")

        FormEditText("Allocation Date", "10 Jan 2022")

//        FormEditText("Location", "Pune")
        val location = listOf("Allahabad", "ArunachalPradesh", "Araku", "Pune", "Thane","Delhi","Goa","Hyderabad")
        FormExposedDropDown("Location",location)

        FormEditText("Father/Husband Name", "FoRam")

        FormEditText("Cash Type", "AAA")

        FormEditText("Total Due", "656200")

        FormEditText("Address", "F302,Srinilayam,SairamColony,West")

        FormEditText("Landmark", "Near Temple")

//        FormEditText("Pin code", "400401")
        FormExposedDropDown("Pin",pin)

        FormEditText("Email", "abc@gmail.com")

        FormEditText("Mobile No", "9502412221")

        FormEditText("Agreement Date", "15 Jan 2022")

        FormEditText("DOB", "10 Oct 1990")

        FormEditText("PAN", "AOXPR1122Q")
        FormExposedDropDown("Options",feildneOtions)

        FormEditText("Guarantor", "Sandeep Krishna")

        FormEditText("Guarantor Designation", "Doctor")
    }
}

@Composable
fun ProposalFormFinal(){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        FormHeader(weight = 1f,MaterialTheme.colors.primaryVariant)
        FormBody(weight = 8f)
        FormFooter(weight = 1f)
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview(){
   ProposalFormFinal()
}