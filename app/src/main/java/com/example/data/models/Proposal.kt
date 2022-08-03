package com.example.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.utils.Constants.PROPOSAL_TABLE

@Entity(tableName = PROPOSAL_TABLE)
data class Proposal(
    @PrimaryKey(autoGenerate = true)
     val id : Int = 0,
     val proposal_no : String,
     val dob : String,
     val company : String,
     val division : String,
     val location : String,
     val customer_name : String,
     val email : String,
     val mobile_no : String,
     val age : Int,
     val total_due : Double,
     val pin : Int
)