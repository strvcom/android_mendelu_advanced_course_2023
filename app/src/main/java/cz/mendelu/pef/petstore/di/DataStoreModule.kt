package cz.mendelu.pef.compose.va1todo

import cz.mendelu.pef.petstore.PetsApplication
import cz.mendelu.pef.petstore.datastore.DataStoreRepositoryImpl
import cz.mendelu.pef.petstore.datastore.IDataStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideDatastoreRepository(): IDataStoreRepository =
        DataStoreRepositoryImpl(PetsApplication.appContext)

}
