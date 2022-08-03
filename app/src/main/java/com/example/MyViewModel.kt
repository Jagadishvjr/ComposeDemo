package com.example

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor() : ViewModel() {

    val emailAddress = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")

    val valid = MediatorLiveData<Boolean>().apply {
        addSource(emailAddress) {
            val valid = isFormValid(it)
            Log.d(it, valid.toString())
            value = valid
        }
    }

    val validPassword = MediatorLiveData<Boolean>().apply {
        addSource(password) {
            val validPassword = isFormValid(it)
            Log.d(it, validPassword.toString())
            value = validPassword
        }
    }

    fun isFormValid(emailAddress: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()
    }

    fun isValidPassword(password: String): Boolean {
        return Patterns.PHONE.matcher(password).matches()
    }
}