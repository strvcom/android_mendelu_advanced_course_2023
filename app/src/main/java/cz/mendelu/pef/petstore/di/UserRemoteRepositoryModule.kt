package cz.mendelu.pef.petstore.di

import cz.mendelu.pef.petstore.communication.user.IUserRemoteRepository
import cz.mendelu.pef.petstore.communication.user.UserAPI
import cz.mendelu.pef.petstore.communication.user.UserRemoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UserRemoteRepositoryModule {

    @Provides
    @Singleton
    fun provideUserRemoteRepository(userAPI: UserAPI): IUserRemoteRepository {
        return UserRemoteRepositoryImpl(userAPI)
    }
}
