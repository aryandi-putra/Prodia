package com.aryandi.prodia.di

import com.aryandi.util.DateParser
import com.aryandi.util.DateParserImpl
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