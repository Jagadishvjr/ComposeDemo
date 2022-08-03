package com.example.formbuilder.composables.dropdowns.outline

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Size
import com.example.composedemo.R
import com.example.formbuilder.BaseState
import com.example.formbuilder.TextFieldState
import com.example.formbuilder.Transform
import com.example.formbuilder.Validators


class OutlinedDropDownStateTest(){

        var enabled by mutableStateOf(false)
        var value by mutableStateOf("")
        var label by mutableStateOf("label")
        var selectedIndex by mutableStateOf(-1)
        var size by mutableStateOf(Size.Zero)
        val icon:Int
        @Composable get() = if(enabled){
            R.drawable.ic_dropdown_arrow_up
        }else{
            R.drawable.ic_dropdown_arrow_down
        }
        val items = (1..11).map{
            if(it == 1){
                "Select"
            }else{
                "Option ${it-1}"
            }

        }


        fun onEnabled(newValue:Boolean){
            enabled = newValue
        }
        fun onSelectedIndex(newVal:Int){
            selectedIndex = newVal
            value = items[selectedIndex]
        }
        fun onSize(newVal:Size){
            size = newVal
        }
    }


@Composable
fun remeberOutlinedDropDownStateTest() = remember { //TODO chenge remember to rememberSavable
    OutlinedDropDownStateTest()
}