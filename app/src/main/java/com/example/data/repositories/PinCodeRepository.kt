package com.example.data.repositories

import com.example.data.dao.PinDao
import com.example.data.models.PinCode
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class PinCodeRepository @Inject constructor(private val pinDao: PinDao) {

    val getAllPinCodesV2 : Flow<List<PinCode>> = pinDao.getAllPinCodesV2()
}