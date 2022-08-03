package com.example.formbuilder

fun String.isNumeric(): Boolean {
    return this.toIntOrNull()?.let { true } ?: false
}