package cz.mendelu.pef.petstore.di

import cz.mendelu.pef.petstore.communication.user.IUserRemoteRepository
import cz.mendelu.pef.petstore.fake.FakeUserRemoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RemoteRepositoryModule::class],
)
abstract class TestUserRemoteRepositoryBinder {
    @Binds
    abstract fun provideUserRemoteRepository(service: FakeUserRemoteRepositoryImpl): IUserRemoteRepository
}
