package com.example.formbuilder.composables.textfields

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.composedemo.R
import com.example.formbuilder.TextFieldState

//Test
@Composable
fun TrailingClearButton(textFieldState : TextFieldState)
{
    Icon(painter = painterResource(id =  R.drawable.ic_back_remove_text),
        contentDescription = "clear text",
        modifier = Modifier.clickable{
            textFieldState.value = ""
        }
    )
}

//Test
val trailingIcon = @Composable{}

//FormOutLinedTextInputField
@Composable
fun FormOutLinedTextInputField(
    labelName : String,
    state : TextFieldState,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeActionVal: ImeAction = ImeAction.Next
){
    // ----------->Start
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(62.dp)
            .padding(
                start = 5.dp,
                end = 5.dp
            ),
        value = state.value,
        isError = state.hasError,
        readOnly = state.isReadOnly,
        colors = if(state.isReadOnly){
            TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.White,
                disabledTextColor= LocalContentColor.current.copy(LocalContentAlpha.current))
        } else {
            TextFieldDefaults.outlinedTextFieldColors()
               },
        label = { Text(labelName) },
        onValueChange = { state.change(it) },
        trailingIcon = {
            Icon(painter = painterResource(
                id =  if(state.isReadOnly) R.drawable.ic_clear_button_disabled else R.drawable.ic_back_remove_text
            ),
                contentDescription = "clear text",
                modifier = Modifier.clickable{
                    if(!state.isReadOnly)
                    state.value = ""
                }
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeActionVal)
    )

    if (state.hasError) Text(state.errorMessage, color = Color.Red)

    Spacer(modifier = Modifier.height(20.dp))
    // ----------->End
}