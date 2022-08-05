package com.example.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.data.models.PinCode
import kotlinx.coroutines.flow.Flow

@Dao
interface PinDao {

    @Query("SELECT * FROM pincode_table ORDER BY id ASC")
    fun getAllPinCodesV2() : Flow<List<PinCode>>

}