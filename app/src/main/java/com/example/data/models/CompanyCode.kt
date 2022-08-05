package com.example.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.utils.Constants

@Entity(tableName = Constants.COMPANY_TABLE)
data class CompanyCode(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val companyCode: String,
    val gs_active: Boolean = true,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP") val gs_modified: Long
)