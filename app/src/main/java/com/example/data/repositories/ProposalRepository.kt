package com.example.data.repositories

import com.example.data.dao.ProposalDao
import com.example.data.models.*
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList
import javax.inject.Inject

@ViewModelScoped
class ProposalRepository @Inject constructor(private val proposalDao: ProposalDao) {

    val getAllPinCodes : Flow<List<PinCode>> = proposalDao.getAllPinCodes()

    val getAllCompCodes : Flow<List<CompanyCode>> = proposalDao.getCompCodes()
    val getDivisionCodes : Flow<List<DivisionCode>> = proposalDao.getDivisionCodes()
    val getLocationCodes : Flow<List<LocationCode>> = proposalDao.getLocationCodes()

    val getProposals: Flow<List<Proposal>> = proposalDao.getAllProposals()

    fun getSelectedProposal(id:Int):Flow<Proposal>{
        return proposalDao.getSelectedProposal(id)
    }

    suspend fun addProposal(proposal: Proposal){
        proposalDao.addProposal(proposal=proposal)
    }

    suspend fun updateProposal(proposal: Proposal){
        proposalDao.updateProposal(proposal = proposal)
    }

    @OptIn(FlowPreview::class)
    suspend fun <T> Flow<List<T>>.flattenToList() =
        flatMapConcat { it.asFlow() }.toList()
}