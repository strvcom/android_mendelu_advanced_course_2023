package cz.mendelu.pef.petstore.di

import cz.mendelu.pef.petstore.communication.pets.IPetsRemoteRepository
import cz.mendelu.pef.petstore.communication.pets.PetsAPI
import cz.mendelu.pef.petstore.communication.pets.PetsRemoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PetsRemoteRepositoryModule {

    @Provides
    @Singleton
    fun providePetsRemoteRepository(petsAPI: PetsAPI): IPetsRemoteRepository {
        return PetsRemoteRepositoryImpl(petsAPI)
    }

}
