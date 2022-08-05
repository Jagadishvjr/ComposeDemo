package com.example.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.models.PinCode
import com.example.data.models.Proposal
import com.example.data.repositories.ProposalRepository
import com.example.formbuilder.*
import com.example.forms.proposals.Constants
import com.example.forms.proposals.Constants.FIELD_NAME_CUSTOMER_COMPANY
import com.example.forms.proposals.Constants.FIELD_NAME_CUSTOMER_DIVISION
import com.example.forms.proposals.Constants.FIELD_NAME_CUSTOMER_LOCATION
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProposalDetailsScreenViewModel @Inject constructor(
    private val repository : ProposalRepository
) : ViewModel() {

    init {
        initPinCodes()
        initCompCodes()
        initDivCodes()
        initLocCodes()
    }

    private val _selectedProposal: MutableStateFlow<Proposal?> = MutableStateFlow(null)
    val selectedProposal: StateFlow<Proposal?> = _selectedProposal

    fun getSelectedTask(id: Int) {
        viewModelScope.launch {
            repository.getSelectedProposal(id = id).collect { proposal ->
                Log.d("getSelectedProposal", "getSelectedTask: ${proposal.proposal_no}")
                _selectedProposal.value = proposal
            }
        }
    }

    //val formState: FormState<BaseState<out Any>> = remember { viewModel.formState }
    private val _selectedFormState: MutableStateFlow<FormState<BaseState<out Any>>> = MutableStateFlow(FormState(fieldsList(
        Proposal(
        id = 0,
        proposal_no = "NA",
        company = "",
        division = "",
        location = "",
        customer_name = "",
        email = "",
        dob = "",
        mobile_no = "",
        age = 12,
        total_due = 0.0,
        pin = 0,
    ))))

    fun checkFormStatus(){
        val state = selectedFormState.value.getState<DropDownState>(FIELD_NAME_CUSTOMER_COMPANY)
        val stateDiv = selectedFormState.value.getState<DropDownState>(FIELD_NAME_CUSTOMER_DIVISION)
        val stateLoc = selectedFormState.value.getState<DropDownState>(FIELD_NAME_CUSTOMER_LOCATION)
        if(state.value.equals("AXIS")){
            stateDiv.value = ""
            stateLoc.value = ""
        }
        Log.d("getSelectedProposal", "company current value is: ${state.value}")

    }
    val selectedFormState: StateFlow<FormState<BaseState<out Any>>> = _selectedFormState


    fun getSelectedFormState(id: Int) {
        viewModelScope.launch {
            repository.getSelectedProposal(id = id).collect { proposal ->
                Log.d("getSelectedProposal", "getSelectedTask: ${proposal.proposal_no}")
                _selectedFormState.value = FormState(fieldsList(proposal))
            }
        }
    }

    //private val _allPinCodes : MutableStateFlow<List<PinCode?>> = MutableStateFlow(emptyList())
    private var _pinList : MutableList<String> = mutableListOf()
    //val allPinCodes : StateFlow<List<PinCode?>> = _allPinCodes

    fun initPinCodes() {
        viewModelScope.launch {
            repository.getAllPinCodes.collect{ pinCode ->
                //_allPinCodes.value = pinCode
                for(pin in pinCode){
                    _pinList.add("${pin.pincode}")
                }
                Log.d("getSelectedProposal", "getSelectedTask: ${_pinList}")
            }
        }
    }

    private var _compCodes : MutableList<String> = mutableListOf()
    fun initCompCodes() {
        viewModelScope.launch {
            repository.getAllCompCodes.collect{ compCode ->
                for(comp in compCode){
                    _compCodes.add(comp.companyCode)
                    Log.d("getSelectedProposal", "comp.companyCode: ${comp.companyCode}")
                }
                Log.d("getSelectedProposal", "getSelectedTask: ${compCode}")
            }
        }
    }

    private var _divCodes : MutableList<String> = mutableListOf()
    fun initDivCodes() {
        viewModelScope.launch {
            repository.getDivisionCodes.collect{ divCodes ->
                for(divCode in divCodes){
                    _divCodes.add(divCode.divisionCode)
                }
                Log.d("getSelectedProposal", "getSelectedTask: ${_divCodes}")
            }
        }
    }

    private var _locCodes : MutableList<String> = mutableListOf()
    fun initLocCodes() {
        viewModelScope.launch {
            repository.getLocationCodes.collect{ locCodes ->
                for(locCode in locCodes){
                    _locCodes.add(locCode.locationCode)
                }
                Log.d("getSelectedProposal", "getSelectedTask: ${_locCodes}")
            }
        }
    }


   // val formState = FormState(fieldsList())

    private fun fieldsList(proposal : Proposal) : List<BaseState<out Any>>{
        val fields : List<BaseState<out Any>> = listOf(
            DateState(
                name = Constants.FIELD_NAME_CUSTOMER_DOB,
                initial="10-OCT-1987",
                validators = listOf(
                    Validators.Required(message = "${Constants.FIELD_NAME_CUSTOMER_DOB} ${Constants.LAST_PART_OF_MANDATORY_ERROR}")
                ),
            ),
            DropDownState(
                name = Constants.FIELD_NAME_CUSTOMER_PIN,
                list = if(!_pinList.isNullOrEmpty()) _pinList else mutableListOf("123"),
                initial="${proposal.pin}",
                enabled=false,
                validators = listOf(
                    Validators.Required(message = "${Constants.FIELD_NAME_CUSTOMER_PIN} ${Constants.LAST_PART_OF_MANDATORY_ERROR}")
                ),
            ),
            DropDownState(
                name = Constants.FIELD_NAME_CUSTOMER_COMPANY,
                list = if(!_compCodes.isNullOrEmpty()) _compCodes else mutableListOf("123"),
                initial=proposal.company,
                enabled=false,
                validators = listOf(
                    Validators.Required(message = "${Constants.FIELD_NAME_CUSTOMER_COMPANY} ${Constants.LAST_PART_OF_MANDATORY_ERROR}")
                ),
            ),
            DropDownState(
                name = Constants.FIELD_NAME_CUSTOMER_DIVISION,
                list = if(!_divCodes.isNullOrEmpty()) _divCodes else mutableListOf("123"),
                initial=proposal.division,
                enabled=false
            ),
            DropDownState(
                name = Constants.FIELD_NAME_CUSTOMER_LOCATION,
                list = if(!_locCodes.isNullOrEmpty()) _locCodes else mutableListOf("123"),
                initial=proposal.location,
                enabled=false,
                validators = listOf(
                    Validators.Required(message = "${Constants.FIELD_NAME_CUSTOMER_LOCATION} ${Constants.LAST_PART_OF_MANDATORY_ERROR}")
                ),
            ),
            TextFieldState(
                name = Constants.FIELD_NAME_CUSTOMER_PROPOSAL_NO,
                initial = proposal.proposal_no,
                isReadOnly = true,
                placeholder ="PG/040/A/400304",
                validators = listOf(
                    Validators.Required(message = "${Constants.FIELD_NAME_CUSTOMER_PROPOSAL_NO} ${Constants.LAST_PART_OF_MANDATORY_ERROR}")
                ),
            ),
            TextFieldState(
                name = Constants.FIELD_NAME_CUSTOMER_NAME,
                initial = "",
                transform = { it.trim().lowercase() },
                validators = listOf(
                    Validators.Required(message = "${Constants.FIELD_NAME_CUSTOMER_NAME} ${Constants.LAST_PART_OF_MANDATORY_ERROR}")
                ),
            ),
            TextFieldState(
                name = Constants.FIELD_NAME_CUSTOMER_TOTAL_DUE,
                initial = "",
                validators = listOf(
                    Validators.Required(message = "${Constants.FIELD_NAME_CUSTOMER_TOTAL_DUE} ${Constants.LAST_PART_OF_MANDATORY_ERROR}")
                ),
            ),
            TextFieldState(
                name = Constants.FIELD_NAME_CUSTOMER_PHONE,
                initial = "",
                transform = { it.trim().lowercase() },
                validators = listOf(
                    Validators.Phone()
                ),
            ),
            TextFieldState(
                name = "email",
                initial = "",
                transform = { it.trim().lowercase() },
                validators = listOf(Validators.Email()),
            ),
            TextFieldState(
                name = "age",
                initial = "",
                transform = {
                    if(it.isNotEmpty()) it.toInt() else 0
                },
                validators = listOf(Validators.MinValue(limit = 18, message = "too young"))
            )
        )
        return fields
    }

}