package com.example.formbuilder

import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Size
import com.example.formbuilder.composables.dropdowns.outline.OutlinedDropDownState

/**
 * SelectState is a state class that holds the selected values from a selection such as checkboxes.
 * In this case the user can make several selections and the state will hold the selected values.
 *
 * @param initial the initial value/state of the field. By default it is an empty list so no values are selected.
 *
 * @param name the name of the state used to get an instance of the state from the form builder.
 * using the [FormBuilder.getState] method.
 *
 * @param transform the transformation function used to transform the values of the state to a desired type.
 * This function will be applied to the value before it is returned.
 *
 * @param validators a list of [Validators] applied to the state's value.
 *
 * @author JagadeeshReddy
 * @created 27/07/2022
 */
class DropDownStateV2(
    name: String,
    initial: String,
    selectedVal: String,
    enabled : Boolean,
    list: MutableList<String> = mutableListOf(),
    transform: Transform<MutableList<String>>? = null,
    validators: List<Validators> = listOf(),
) : BaseState<MutableList<String>>(initial = list, name = name, transform = transform, validators) {

    /**
     * The variable that holds the selected value of the dropdown.
     *
     */
    override var value: MutableList<String> by mutableStateOf(list)

    var list: MutableList<String> by mutableStateOf(list)
    var selectedVal: String by mutableStateOf(initial)

    var enabled: Boolean by mutableStateOf(enabled)
    var selectedIndex by mutableStateOf(-1)
    var size by mutableStateOf(Size.Zero)

    fun onEnabled(newValue:Boolean){
        enabled = newValue
    }
    fun onSelectedIndex(newVal:Int){
        selectedIndex = newVal
        selectedVal = list[selectedIndex]
    }
    fun onSize(newVal: Size){
        size = newVal
    }


    /**
     * Runs all [validators] passed in to th state class against the state's value.
     *
     * @throws Exception if the used [Validators] class is not supported.
     * @return true if all validators pass, false otherwise.
     */
    override fun validate(): Boolean {
        val validations = validators.map {
            when (it) {
                is Validators.Required -> validateRequired(it.message)
                is Validators.Custom -> validateCustom(it.function, it.message)
                else -> throw Exception("${it::class.simpleName} validator cannot be called on checkbox state. Did you mean Validators.Custom?")
            }
        }
        return validations.all { it }
    }


    /**
     *This function validates that the state's value is not empty.
     * @param message the error message to be displayed if the state's value does not meet the validation criteria.
     * @return true if the state's value is not empty.
     */
    private fun validateRequired(message: String): Boolean {
        val valid = selectedVal.isNotEmpty()
        if (!valid) showError(message)
        return valid
    }

    /**
     *This function validates that the state's value meets the criteria defined in the custom lambda
     * function passed in to the [Validators.Custom] class.
     *
     * @param function the custom lambda function that will be applied to the state's value.
     * @param message the error message to be displayed if the state's value does not meet the validation criteria.
     * @return true if the state's value meets the criteria defined in the custom lambda function passed in to the [Validators.Custom] class.
     */
    private fun validateCustom(function: (MutableList<String>) -> Boolean, message: String): Boolean {
        val valid = function(value)
        if (!valid) showError(message)
        return valid
    }

}

@Composable
fun remeberDropDownStateV2() = remember { //TODO chenge remember to rememberSavable
    DropDownState(
        name = "",
        list= mutableListOf(),
        initial="",
        selectedVal="",
        enabled=false
    )
}