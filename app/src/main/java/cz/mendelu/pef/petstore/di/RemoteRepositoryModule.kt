package cz.mendelu.pef.petstore.di

import cz.mendelu.pef.petstore.communication.pets.PetsAPI
import cz.mendelu.pef.petstore.communication.pets.PetsRemoteRepositoryImpl
import cz.mendelu.pef.petstore.communication.user.UserAPI
import cz.mendelu.pef.petstore.communication.user.UserRemoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RemoteRepositoryModule {

    @Provides
    @Singleton
    fun providePetsRemoteRepository(petsAPI: PetsAPI): PetsRemoteRepositoryImpl {
        return PetsRemoteRepositoryImpl(petsAPI)
    }

    @Provides
    @Singleton
    fun provideUserRemoteRepository(userAPI: UserAPI): UserRemoteRepositoryImpl {
        return UserRemoteRepositoryImpl(userAPI)
    }
}
