package com.example.formbuilder.composables.dropdowns.outline

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.material.*
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.composedemo.R
import com.example.formbuilder.DropDownState

@Composable
fun FormOutlinedDropDown(state: DropDownState){
    Column(
        modifier = Modifier.fillMaxWidth()
            .height(62.dp)
            .padding(
                start = 5.dp,
                end = 5.dp
            )
    ) {
        Box() {
            OutlinedTextField(
                value =state.selectedVal ,
                readOnly = state.isReadOnly,
                onValueChange ={},//will not respond to keyboard
                label = {Text(text = state.name)},
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = if(state.enabled)
                            R.drawable.ic_dropdown_arrow_up
                        else
                            R.drawable.ic_dropdown_arrow_down
                        ),
                        contentDescription =null,
                        Modifier.clickable {
                            if(!state.isReadOnly)
                            state.onEnabled(!(state.enabled))
                        }
                    )
                },
                modifier = Modifier.fillMaxWidth()
                    .onGloballyPositioned {
                        state.onSize(it.size.toSize())
                    },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next)
            )
            DropdownMenu(
                expanded = state.enabled,
                onDismissRequest = {
                    state.onEnabled(false)
                },
                modifier = Modifier.width(with(LocalDensity.current){state.size.width.toDp()})
            ) {
                if(!state.isReadOnly)
                state.list.forEachIndexed{ index, s ->
                    DropdownMenuItem(onClick = {
                        state.onSelectedIndex(index)
                        state.onEnabled(false)
                    }) {
                        val isSelected = state.selectedVal == s
                        val style = if (isSelected) {
                            MaterialTheme.typography.body1.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colors.secondary
                            )
                        } else {
                            MaterialTheme.typography.body1.copy(
                                fontWeight = FontWeight.Normal,
                                color = MaterialTheme.colors.onSurface
                            )
                        }
                        Text(text = s, style = style )
                    }
                }
            }
        }
    }


}