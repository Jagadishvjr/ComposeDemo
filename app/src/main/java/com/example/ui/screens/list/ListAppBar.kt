package com.example.ui.screens.list

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ListAppBar(){
    DefaultListAppBar()
}


@Composable
fun DefaultListAppBar(){
    TopAppBar(
        title = {
            Text(text = "ToDo List")
        },
        backgroundColor = MaterialTheme.colors.primary
    )
}


@Preview
@Composable
private fun DefaultPreview(){
    DefaultListAppBar()
}