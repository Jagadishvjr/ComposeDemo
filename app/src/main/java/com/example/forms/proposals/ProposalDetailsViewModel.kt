package com.example.forms.proposals


import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.formbuilder.*
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
import com.example.forms.proposals.Constants.LAST_PART_OF_MANDATORY_ERROR

class ProposalDetailsViewModel : ViewModel() {

//    val formStateV2 = FormState(
//        fields = listOf(
//            DropDownState(
//                name = "testfield",
//                list = mutableListOf("ICICI", "HDFC", "AXIS", "HSBC", "TATA Finance","Bajaj Auto","Reliance","Manapuram"),
//                initial="",
//                enabled=false
//            ),
//            ChoiceState(
//                initial = "",
//                name = "gender",
//                validators = listOf()
//            ),
//            TextFieldState(
//                name = FIELD_NAME_CUSTOMER_PROPOSAL_NO,
//                initial = "PG/040/A/400304",
//                transform = { it.trim().lowercase() },
//                validators = listOf(
//                    Validators.Required(message = "Mandatory Field")),
//            ),
//            TextFieldState(
//                name = FIELD_NAME_CUSTOMER_NAME,
//                initial = "",
//                transform = { it.trim().lowercase() },
//                validators = listOf(
//                    Validators.Required(message = "Mandatory Field")),
//            ),
//            TextFieldState(
//                name = FIELD_NAME_CUSTOMER_TOTAL_DUE,
//                initial = "",
//                transform = { it.trim().lowercase() },
//                validators = listOf(
//                    Validators.Required(message = "Mandatory Field")
//                ),
//            ),
//            TextFieldState(
//                name = FIELD_NAME_CUSTOMER_PHONE,
//                initial = "",
//                transform = { it.trim().lowercase() },
//                validators = listOf(
//                    Validators.Phone()
//                ),
//            ),
//            TextFieldState(
//                name = "email",
//                initial = "",
//                transform = { it.trim().lowercase() },
//                validators = listOf(Validators.Email()),
//            ),
//            SelectState(
//                initial = mutableListOf("Chess"),
//                name = "hobbies"
//            )
//        )
//    )
    val pinList = mutableListOf("400401", "400402", "400403","400404","400405","400406","400407","400408","400409","400410","400411","400412",
    "500401", "500402", "500403","500404","500405","500406","500407","500408","500409","500410","500411","500412",
    "600401", "600402", "600403","600404","600405","600406","600407","600408","600409","600410","600411","600412")
    val formState = FormState(
        fields = listOf(
            DateState(
                name = FIELD_NAME_CUSTOMER_DOB,
                initial="10-OCT-1987",
                validators = listOf(
                    Validators.Required(message = "$FIELD_NAME_CUSTOMER_DOB $LAST_PART_OF_MANDATORY_ERROR")
                ),
            ),
            DropDownState(
                name = FIELD_NAME_CUSTOMER_PIN,
                list = pinList,
                initial="",
                enabled=false,
                validators = listOf(
                    Validators.Required(message = "$FIELD_NAME_CUSTOMER_PIN $LAST_PART_OF_MANDATORY_ERROR")
                ),
            ),
            DropDownState(
                name = FIELD_NAME_CUSTOMER_COMPANY,
                list = mutableListOf("ICICI", "HDFC", "AXIS", "HSBC", "TATA Finance","Bajaj Auto","Reliance","Manapuram"),
                initial="",
                enabled=false,
                validators = listOf(
                    Validators.Required(message = "$FIELD_NAME_CUSTOMER_COMPANY $LAST_PART_OF_MANDATORY_ERROR")
                ),
            ),
            DropDownState(
                name = FIELD_NAME_CUSTOMER_DIVISION,
                list = mutableListOf("Car", "Tractor", "Auto", "Bus", "Harvester","Jcb","Two wheeler","Heavy vehicle"),
                initial="",
                enabled=false
            ),
            DropDownState(
                name = FIELD_NAME_CUSTOMER_LOCATION,
                list = mutableListOf("Allahabad", "ArunachalPradesh", "Araku", "Pune", "Thane","Delhi","Goa","Hyderabad"),
                initial="",
                enabled=false,
                validators = listOf(
                    Validators.Required(message = "$FIELD_NAME_CUSTOMER_LOCATION $LAST_PART_OF_MANDATORY_ERROR")
                ),
            ),
            ChoiceState(
                initial = "",
                name = "gender",
                validators = listOf()
            ),
            TextFieldState(
                name = FIELD_NAME_CUSTOMER_PROPOSAL_NO,
                initial = "PG/040/A/400304",
                isReadOnly = true,
                placeholder ="PG/040/A/400304",
                validators = listOf(
                    Validators.Required(message = "$FIELD_NAME_CUSTOMER_PROPOSAL_NO $LAST_PART_OF_MANDATORY_ERROR")
                ),
            ),
            TextFieldState(
                name = FIELD_NAME_CUSTOMER_NAME,
                initial = "",
                transform = { it.trim().lowercase() },
                validators = listOf(
                    Validators.Required(message = "$FIELD_NAME_CUSTOMER_NAME $LAST_PART_OF_MANDATORY_ERROR")
                ),
            ),
            TextFieldState(
                name = FIELD_NAME_CUSTOMER_TOTAL_DUE,
                initial = "",
                validators = listOf(
                    Validators.Required(message = "$FIELD_NAME_CUSTOMER_TOTAL_DUE $LAST_PART_OF_MANDATORY_ERROR")
                ),
            ),
            TextFieldState(
                name = FIELD_NAME_CUSTOMER_PHONE,
                initial = "",
                transform = { it.trim().lowercase() },
                validators = listOf(
                    Validators.Phone()
                ),
            ),
            TextFieldState(
                name = "email",
                initial = "",
                transform = { it.trim().lowercase() },
                validators = listOf(Validators.Email()),
            ),
            TextFieldState(
                name = "age",
                initial = "",
                transform = {
                    if(it.isNotEmpty()) it.toInt() else 0
                            },
                validators = listOf(Validators.MinValue(limit = 18, message = "too young"))
            ),
            SelectState(
                initial = mutableListOf("Chess"),
                name = "hobbies"
            )
        )
    )


    fun submit() : Boolean{
        if (formState.validate()){
//            val divState = formState.getState<DropDownState>(FIELD_NAME_CUSTOMER_DIVISION)
//            val compState = formState.getState<DropDownState>(FIELD_NAME_CUSTOMER_COMPANY)
//            Log.d("Validators","divState val ${divState.value}")
//            if(divState.value == "Tractor"){
//                divState.value = ""
//                compState.value = ""
//            }
            val data = formState.getData(ProposalFormFieldsData::class)
            Log.d("Validators", "submit: data from the form $data")
            return true
        }
        return false
    }

    fun updateFormAsReadOnly(){
        //formState.getState<TextFieldState>(FIELD_NAME_CUSTOMER_PROPOSAL_NO).isReadOnly = true
        formState.getState<DateState>(FIELD_NAME_CUSTOMER_DOB).isReadOnly = true
        formState.getState<DropDownState>(FIELD_NAME_CUSTOMER_COMPANY).isReadOnly = true
        formState.getState<DropDownState>(FIELD_NAME_CUSTOMER_DIVISION).isReadOnly = true
        formState.getState<DropDownState>(FIELD_NAME_CUSTOMER_LOCATION).isReadOnly = true
        formState.getState<TextFieldState>(FIELD_NAME_CUSTOMER_NAME).isReadOnly = true
        formState.getState<TextFieldState>(FIELD_NAME_CUSTOMER_EMAIL).isReadOnly = true
        formState.getState<TextFieldState>(FIELD_NAME_CUSTOMER_PHONE).isReadOnly = true
        formState.getState<TextFieldState>(FIELD_NAME_CUSTOMER_AGE).isReadOnly = true
        formState.getState<TextFieldState>(FIELD_NAME_CUSTOMER_TOTAL_DUE).isReadOnly = true
        formState.getState<DropDownState>(FIELD_NAME_CUSTOMER_PIN).isReadOnly = true
    }

}