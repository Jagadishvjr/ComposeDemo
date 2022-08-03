package com.example.composedemo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.MyViewModel
import com.example.composedemo.composables.*
import com.example.composedemo.ui_old.theme.ComposeDemoTheme
import com.example.forms.proposals.ProposalDetailsViewModel
import com.example.forms.proposals.ProposalFormScreen
import dagger.hilt.android.AndroidEntryPoint

class MainActivityOld : ComponentActivity() {

    private val viewModel: MyViewModel by viewModels()
    private val proposalDetailsViewModel: ProposalDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val emailState by viewModel.emailAddress.observeAsState()
            val paswordState by viewModel.password.observeAsState()

            val isFormValid by viewModel.valid.observeAsState()
            val isValidPassword by viewModel.validPassword.observeAsState()
            MaterialTheme {
                ProposalFormScreen(proposalDetailsViewModel)
            //ContentV2(proposalDetailsViewModel)
            //ProposalFormFinal()
                //ProposalDropDownFieldsFinal()
                //FormDropDown()

            }
        }
    }
}




private fun tempPasswordUi(){
    //                Column {
//                    LoginFormUI(
//                        passwordHideIcon = R.drawable.ic_password_hide,
//                        passwordShowIcon = R.drawable.ic_password_show
//                    )

    //WelcomeView()
    //ProposalFormFinal()
    //  }
}
@Composable
fun WelcomeView() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),

        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome to Pewise",
            color = Color.Blue
        )
    }

}

@Composable
fun ProposalForm(
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
                .padding(2.dp)
                .border(width = 1.dp, color = Color.Blue)
                .padding(2.dp)
        ) {
            FormEditText("Proposal No","PG/040/A/121/2002")
            
            FormEditText("Division Name","CAR")
            
            FormEditText("Company","ICICI")
            
            FormEditText("Party name","Ram")
            
            FormEditText("Allocation Date","10 Jan 2022")
            
            FormEditText("Location","Pune")
            
            FormEditText("Father/Husband Name","FoRam")
            
            FormEditText("Cash Type","AAA")
            
            FormEditText("Total Due","656200")
            
            FormEditText("Address","F302,Srinilayam,SairamColony,West")
            
            FormEditText("Landmark", "Near Temple")
            
            FormEditText("Pin code","400401")
            
            FormEditText("Email","abc@gmail.com")
            
            FormEditText("Mobile No","9502412221")
            
            FormEditText("Agreement Date","15 Jan 2022")
            
            FormEditText("DOB","10 Oct 1990")
            
            FormEditText("PAN","AOXPR1122Q")
            
            FormEditText("Guarantor","Sandeep Krishna")
            
            FormEditText("Guarantor Designation","Doctor")

            Spacer(modifier = Modifier.size(4.dp))
            Button(
                modifier = Modifier
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
                Text(text = "Upload")


            }
        }
    }

}

@Composable
fun LoginForm(
    emailText: String = "",
    passwordText: String = "",
    onEmailChanged: (String) -> Unit = {},
    onPasswordChanged: (String) -> Unit = {},
    isFormValid: Boolean = false
) {
    val context = LocalContext.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(width = 1.dp, color = Color.Gray)
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = emailText,
            label = { Text("Email") },
            onValueChange = onEmailChanged,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = passwordText,
            label = { Text("Password") },
            onValueChange = onPasswordChanged,
            modifier = Modifier.fillMaxWidth()

        )

        
        Button(
            onClick = {
                Toast.makeText(context, "Login clicked", Toast.LENGTH_SHORT).show()
            }) {
            Text(text = "Login")

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeDemoTheme {
        ProposalFormFinal()
    }
}