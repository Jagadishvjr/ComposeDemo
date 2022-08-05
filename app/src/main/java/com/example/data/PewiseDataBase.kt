package com.example.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.dao.PinDao
import com.example.data.dao.ProposalDao
import com.example.data.models.*

@Database(entities = [Proposal::class,PinCode::class,CompanyCode::class, DivisionCode::class, LocationCode::class], version = 1, exportSchema = false)
abstract class PewiseDataBase : RoomDatabase(){

    abstract fun proposalDao()  : ProposalDao
    //abstract fun pinDao()  : PinDao
}

//insert into pincode_table(id,pincode,gs_active,gs_modified) values(5,'202308',1,1234)

//insert into company_table(id,companyCode,gs_active,gs_modified) values(1,'AXIS',1,1234)
//insert into division_table(id,divisionCode,gs_active,gs_modified) values(1,'CAR',1,1234)
//insert into location_table(id,locationCode,gs_active,gs_modified) values(1,'HYD',1,1234)

//insert into proposals_table(id,proposal_no,dob,company,division,location,customer_name,email,mobile_no,age,total_due,pin) values(7,'PG/010/A/10/22027','10-07-1987','HSBC','CAR','PUNE','Chand','abc@gmail.com','9502412231',43,58000.0,508266)
//insert into proposals_table(id,proposal_no,dob,company,division,location,customer_name,email,mobile_no,age,total_due,pin) values(1,'PG/010/A/10/22018','10-07-1987','HSBC','CAR','PUNE','Abc','abc@gmail.com','9502412221',43,58000.0,508266);
//insert into proposals_table(id,proposal_no,dob,company,division,location,customer_name,email,mobile_no,age,total_due,pin) values(2,'PG/010/A/10/22019','10-07-1987','HSBC','CAR','PUNE','Abc2','abc@gmail.com','9502412221',43,58000.0,508266);
//insert into proposals_table(id,proposal_no,dob,company,division,location,customer_name,email,mobile_no,age,total_due,pin) values(3,'PG/010/A/10/22012','10-07-1987','HSBC','CAR','PUNE','Abc3','abc@gmail.com','9502412221',43,58000.0,508266);
//insert into proposals_table(id,proposal_no,dob,company,division,location,customer_name,email,mobile_no,age,total_due,pin) values(4,'PG/010/A/10/22014','10-07-1987','HSBC','CAR','PUNE','Abc4','abc@gmail.com','9502412221',43,58000.0,508266);
//insert into proposals_table(id,proposal_no,dob,company,division,location,customer_name,email,mobile_no,age,total_due,pin) values(5,'PG/010/A/10/22016','10-07-1987','HSBC','CAR','PUNE','Abc5','abc@gmail.com','9502412221',43,58000.0,508266);