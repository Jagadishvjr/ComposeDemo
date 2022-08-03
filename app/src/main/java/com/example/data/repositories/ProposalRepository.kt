package com.example.data.repositories

import com.example.data.dao.ProposalDao
import com.example.data.models.Proposal
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class ProposalRepository @Inject constructor(private val proposalDao: ProposalDao) {

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
}