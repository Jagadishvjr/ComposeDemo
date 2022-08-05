package com.example.data.dao

import androidx.room.*
import com.example.data.models.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ProposalDao {

    @Query("SELECT * FROM pincode_table ORDER BY id ASC")
    fun getAllPinCodes() : Flow<List<PinCode>>

    @Query("SELECT * FROM company_table ORDER BY id ASC")
    fun getCompCodes() : Flow<List<CompanyCode>>

    @Query("SELECT * FROM division_table ORDER BY id ASC")
    fun getDivisionCodes() : Flow<List<DivisionCode>>

    @Query("SELECT * FROM location_table ORDER BY id ASC")
    fun getLocationCodes() : Flow<List<LocationCode>>

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
