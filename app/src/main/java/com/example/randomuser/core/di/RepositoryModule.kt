package com.example.randomuser.core.di

import com.example.randomuser.core.network.RandomUserRepository
import com.example.randomuser.core.network.RandomUserService
import com.example.randomuser.core.utils.NetworkHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Module is to initialize the RepositoryModule for injection
 */
@Module
@InstallIn(ViewModelComponent::class)
object
RepositoryModule {

    /**
     * this provides the instance of randomUserRepository
     */
    @Provides
    @ViewModelScoped
    fun randomUserRepository(
        networkHandler: NetworkHandler,
        service: RandomUserService,
    ): RandomUserRepository {
        return RandomUserRepository.RandomUserRepositoryImpl(networkHandler, service)
    }

}