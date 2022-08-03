package com.example.composedemo.composables

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginFormUI(
    passwordHideIcon : Int,

    passwordShowIcon : Int
){
    LoginFields(passwordHideIcon,passwordShowIcon)
}

@Composable
fun LoginFields(passwordHideIcon:Int,passwordShowIcon: Int){

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        var password by rememberSaveable { mutableStateOf("") }
        var passwordVisibility by rememberSaveable { mutableStateOf(false) }
        var userName by rememberSaveable { mutableStateOf("") }
        val iconEye = if(passwordVisibility)
            painterResource(id = passwordShowIcon)
        else
            painterResource(id = passwordHideIcon)
        var enteredVal =
            Text(
                text = "Welcome to Pewise",
                fontSize = 30.sp,
                color = MaterialTheme.colors.primaryVariant,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(
                        top = 2.dp,
                        bottom = 100.dp
                    )
            )
            OutlinedTextField(
            value = userName,
            label = { Text(text = "UserId")},
            placeholder = { Text(text = "UserId")},
            onValueChange = { enteredUserName ->
             userName = enteredUserName
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            )
        )
        OutlinedTextField(
            value = password,
            placeholder = { Text(text = "Password")},
            label = { Text(text = "Password")},

            trailingIcon = {
                           IconButton(onClick = {
                               passwordVisibility = !passwordVisibility
                           }) {
                               Icon(
                                   painter = iconEye,
                                   contentDescription = "password show"
                               )

                           }
            },
            onValueChange = { enteredPassword ->
                password = enteredPassword
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Go
            ),
            visualTransformation = if(passwordVisibility) VisualTransformation.None
            else PasswordVisualTransformation()
        )
        val context = LocalContext.current
        Button(
            modifier = Modifier
                .padding(
                    bottom = 200.dp,
                    top = 5.dp
                ),
            onClick = {
                    Toast.makeText(context, "Login clicked...", Toast.LENGTH_SHORT).show()

            }) {
            Text(text = "Login",
                fontSize = 25.sp)

        }

    }
}

@Preview
@Composable
private fun DefaultPreview(){
    LoginFormUI(0,0)
}