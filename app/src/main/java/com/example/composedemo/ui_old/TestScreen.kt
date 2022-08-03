package com.example.composedemo.ui_old

import androidx.compose.foundation.layout.Column
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TestFormWithValidations(){
    Column {
        TextField(value = "", onValueChange = {})
    }
}


@Preview
@Composable
private fun DefaultPreview(){
    TestFormWithValidations()
}