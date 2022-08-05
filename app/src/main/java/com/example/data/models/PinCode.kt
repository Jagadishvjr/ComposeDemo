package com.example.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.utils.Constants.PINCODE_TABLE

@Entity(tableName = PINCODE_TABLE)
data class PinCode(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val pincode: Int,
    val gs_active: Boolean = true,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP") val gs_modified: Long
)