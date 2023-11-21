package cz.mendelu.pef.compose.va1todo

import android.content.Context
import cz.mendelu.pef.petstore.datastore.DataStoreRepositoryImpl
import cz.mendelu.pef.petstore.datastore.IDataStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideDatastoreRepository(
        @ApplicationContext context: Context
    ): IDataStoreRepository =
        DataStoreRepositoryImpl(context)

}
