package com.example.composedemo.composables

import android.widget.Toast
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
private fun DefaultPreview(){
    FormTopAppBar()
}

@Composable
fun FormTopAppBar(){
    var showMenu by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
//    TopAppBar(
//        title = { Text( "Form Name Here")},
//        actions = {
//            IconButton(onClick = { Toast.makeText(context, "Favorite", Toast.LENGTH_SHORT) }) {
//                Icon(painter = Icons.Default.Favorite, contentDescription ="" )
//            }
//        }
//    ) {
//
//    }
}