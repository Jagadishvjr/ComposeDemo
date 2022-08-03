package com.example.formbuilder.composables.alertdialogs

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable


@Composable
fun SimpleAlertDialog(
    onConfirmationClicked : () -> Unit = {},
    onCancelClicked : () -> Unit = {},
    dismissButtonName : String = "Cancel",
    confirmButtonName : String = "Ok",
    title : String = "Alert",
    message : String = "Please check the details and confirm",
) {
    AlertDialog(
        onDismissRequest = { },
        confirmButton = {
            TextButton(onClick = onConfirmationClicked)
            { Text(text = confirmButtonName) }
        },
        dismissButton = {
            TextButton(onClick = onCancelClicked)
            { Text(text = dismissButtonName) }
        },
        title = { Text(text = title) },
        text = { Text(text = message) }
    )
}