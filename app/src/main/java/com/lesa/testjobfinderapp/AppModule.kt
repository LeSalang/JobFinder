package com.lesa.testjobfinderapp

import com.lesa.data.JobRepository
import com.lesa.data.JobRepositoryImpl
import com.lesa.network.Api
import com.lesa.network.createApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(): Api {
        return createApi()
    }

    @Provides
    @Singleton
    fun provideJobRepository(api: Api): JobRepository {
        return JobRepositoryImpl(api = api)
    }
}
