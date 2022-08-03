package com.example.formbuilder.composables.datepicker

import android.app.DatePickerDialog
import android.icu.text.DateFormat
import android.util.Log
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import com.example.composedemo.R
import com.example.formbuilder.DateState
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


private fun getStringMonth(month:Int) : String{
    var monthString = "NA"
    when(month){
        1 -> monthString = "JAN"
        2 -> monthString = "FEB"
        3 -> monthString = "MAR"
        4 -> monthString = "APR"
        5 -> monthString = "MAY"
        6 -> monthString = "JUN"
        7 -> monthString = "JUL"
        8 -> monthString = "AUG"
        9 -> monthString = "SEP"
        10 -> monthString = "OCT"
        11 -> monthString = "NOV"
        12 -> monthString = "DEC"
    }
    return monthString
}

private fun getNumberMonth(month:String) : Int{
    var monthNumber = 0
    when(month){
        "JAN"-> monthNumber = 1
        "FEB"-> monthNumber = 2
        "MAR"-> monthNumber = 3
        "APR"-> monthNumber = 4
        "MAY"-> monthNumber = 5
        "JUN"-> monthNumber = 6
        "JUL"-> monthNumber = 7
        "AUG"-> monthNumber = 8
        "SEP"-> monthNumber = 9
        "OCT" -> monthNumber =10
        "NOV" -> monthNumber =11
        "DEC" -> monthNumber =12
    }
    return monthNumber
}

private fun splitDateString(dateString : String) : Map<String, Any>? {
    var map: Map<String, Any>? = null
    val dateValues : List<String> = dateString.split("-")
    if(dateValues.size == 3){
        map =mapOf(
            "day" to dateValues.get(0),
            "month" to dateValues.get(1),
            "year" to dateValues.get(2)
        )
        Log.d("dateValues"," map - $map")
    }else{
        Log.d("dateValues"," size - ${dateValues.size}  data - ${dateValues.toString()}")
    }

 return map
}

private fun getDatePartFromValue(dateString : String,datePart : Int) : Int {
    //datePart 0->day,1->month,2->year
    var returnVal = 0
    val dateValues : List<String> = dateString.split("-")
    if(dateValues.size == 3){
        if(datePart == 1) {
            val m = dateValues.get(datePart)
            if(m.length == 3)
            returnVal = getNumberMonth(m)
        }else{
            returnVal = Integer.parseInt(dateValues.get(datePart))
        }
    }else{
        Log.d("dateValues"," size - ${dateValues.size}  data - ${dateValues.toString()}")
    }
    return returnVal
}

@Composable
fun OutlinedDateField(state: DateState){
    val context = LocalContext.current
    val year: Int
    val month: Int
    val day: Int

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val datePickerDialog : DatePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, yearVal: Int, monthVal: Int, dayOfMonth: Int ->
            val monthForStrMonth = monthVal + 1
            val monthString = getStringMonth(monthForStrMonth)
            Log.d("dateValues"," monthString - $monthString , monthForStrMonth - $monthForStrMonth")
            state.value = "$dayOfMonth-$monthString-$yearVal"
        }, year, month, day
    )
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
                value =state.value ,
                onValueChange ={newVal ->
                    state.value = newVal
                    state.change(newVal)
                },
                readOnly = state.isReadOnly,
                placeholder = {Text(text = "Select Date")},
                label = {Text(text = (state.name).uppercase())},
                modifier = Modifier
                    .fillMaxWidth(),
                trailingIcon = {
                    Icon(painter = painterResource(
                        id =  if(state.isReadOnly) R.drawable.ic_clear_button_disabled else R.drawable.ic_back_remove_text
                    ),
                        contentDescription = "clear date",
                        modifier = Modifier.clickable{
                            if(!state.isReadOnly)
                            state.value = ""
                        }
                    )
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next),
                
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            end = 55.dp
                        ),
                    elevation = null,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Transparent,
                        disabledBackgroundColor = Color.Transparent),
                    //colors = ButtonDefaults.buttonColors(backgroundColor = Color.White.copy(alpha = 0.5f)),
                    onClick = {
                        //splitDateString(state.value)
//                        if((state.value).isNotEmpty()) {
//                            val day_ = getDatePartFromValue(state.value, 0)
//                            val month_ = getDatePartFromValue(state.value, 1)-1
//                            val year_ = getDatePartFromValue(state.value, 2)
//                            datePickerDialog.updateDate(year_, month_, day_)
//                            Log.d("dateValues"," day_ $day_ , month_ $month_, year_ $year_")
//                        }else{
//                            Log.d("dateValues" , "day_ , month_ and year_ are Empty")
//                        }
                        if(!state.isReadOnly)
                        datePickerDialog.show()
                    }) {
                    Text(text = "")
                }
            }
        }
    }
}