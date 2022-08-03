package com.example.di

import android.content.Context
import androidx.room.Room
import com.example.data.PewiseDataBase
import com.example.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideDatabse(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        PewiseDataBase::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideProposalDao(dataBase: PewiseDataBase) = dataBase.proposalDao()
}