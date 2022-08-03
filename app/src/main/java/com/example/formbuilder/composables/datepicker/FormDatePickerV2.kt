import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.composedemo.R
import com.example.formbuilder.composables.datepicker.OutlinedDateFieldState
import com.example.formbuilder.composables.dropdowns.outline.OutlinedDropDownState
import java.text.DateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun DateField(   filedLabel : String = "Select Date"
) {
    val context = LocalContext.current
    val year: Int
    val month: Int
    val day: Int

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val date = remember { mutableStateOf("") }
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            //val initialDate = "$dayOfMonth-$month-$year"
            val formatDate = LocalDate.parse("$dayOfMonth-$month-$year", DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            val reqDateFormatVal = " ${formatDate.dayOfMonth}-${formatDate.month}-${formatDate.year}"
            date.value = reqDateFormatVal
        }, year, month, day
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = date.value)
        Spacer(modifier = Modifier.size(16.dp))
        Button(onClick = {
            datePickerDialog.show()
        }) {
            Text(text = filedLabel)
        }
    }
}

@Composable
fun OutlinedDateFieldV2(state: OutlinedDateFieldState){
    val context = LocalContext.current
    val year: Int
    val month: Int
    val day: Int

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            state.value = "$dayOfMonth-$month-$year"
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
                },
                placeholder = {Text(text = "Select Date")},
                label = {Text(text = state.label)},
                modifier = Modifier
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next)
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    modifier = Modifier.fillMaxSize(),
                    elevation = null,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Transparent,
                        disabledBackgroundColor = Color.Transparent),
                    //colors = ButtonDefaults.buttonColors(backgroundColor = Color.White.copy(alpha = 0.5f)),
                            onClick = {
                    datePickerDialog.show()
                }) {
                    Text(text = "")
                }
            }
        }
    }
}