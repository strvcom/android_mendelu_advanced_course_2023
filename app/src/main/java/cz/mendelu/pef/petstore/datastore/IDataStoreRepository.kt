package cz.mendelu.pef.petstore.datastore

interface IDataStoreRepository {
    suspend fun setLoginSuccessful()
    suspend fun getLoginSuccessful(): Boolean
}