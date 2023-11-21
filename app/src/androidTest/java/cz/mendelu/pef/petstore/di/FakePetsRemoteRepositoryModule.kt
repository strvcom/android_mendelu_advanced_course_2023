package cz.mendelu.pef.petstore.di

import cz.mendelu.pef.petstore.communication.pets.IPetsRemoteRepository
import cz.mendelu.pef.petstore.fake.FakePetsRemoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [PetsRemoteRepositoryModule::class],
)
abstract class FakePetsRemoteRepositoryModule {
    @Binds
    abstract fun providePetsRemoteRepository(service: FakePetsRemoteRepositoryImpl): IPetsRemoteRepository
}
