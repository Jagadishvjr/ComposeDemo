package com.example.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.models.Proposal
import com.example.data.repositories.ProposalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProposalViewModel @Inject constructor(
    private val repository : ProposalRepository
) : ViewModel() {

    private val _allProposals =
        MutableStateFlow<List<Proposal>>(emptyList())

    val allProposals : StateFlow<List<Proposal>> = _allProposals

    fun getAllProposals(){
        viewModelScope.launch {
            repository.getProposals.collect { proposalList ->
                _allProposals.value = proposalList
            }
        }
    }

}