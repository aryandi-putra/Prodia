package com.aryandi.prodia.di

import com.aryandi.prodia.util.DateParser
import com.aryandi.prodia.util.DateParserImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UtilModule {

    @Provides
    fun provideDateParser(): DateParser = DateParserImpl()
}