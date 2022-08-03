package com.example.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.dao.ProposalDao
import com.example.data.models.Proposal

@Database(entities = [Proposal::class], version = 1, exportSchema = false)
abstract class PewiseDataBase : RoomDatabase(){

    abstract fun proposalDao()  : ProposalDao
}
//insert into proposals_table(id,proposal_no,dob,company,division,location,customer_name,email,mobile_no,age,total_due,pin) values(7,'PG/010/A/10/22027','10-07-1987','HSBC','CAR','PUNE','Chand','abc@gmail.com','9502412231',43,58000.0,508266)