package com.example.data.dao

import androidx.room.*
import com.example.data.models.Proposal
import kotlinx.coroutines.flow.Flow

@Dao
interface ProposalDao {

    @Query("SELECT * FROM proposals_table ORDER BY id ASC")
    fun getAllProposals() : Flow<List<Proposal>>

    @Query("SELECT * FROM proposals_table WHERE id=:id")
    fun getSelectedProposal(id:Int): Flow<Proposal>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProposal(proposal: Proposal)

    @Update
    suspend fun updateProposal(proposal: Proposal)

    @Delete
    suspend fun deleteProposal(proposal: Proposal)

}
