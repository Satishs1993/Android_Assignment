package net.bg.satish_assignment.data.repository

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.bg.satish_assignment.data.database.RepoDao
import net.bg.satish_assignment.data.models.Item
import net.bg.satish_assignment.data.network.ImpApiCall
import net.bg.satish_assignment.data.network.services.GithubRepositoryService
import javax.inject.Inject

class GithubRepository @Inject constructor(
    private val apiService: GithubRepositoryService,
    private val dao: RepoDao
) : ImpApiCall {


    suspend fun getRepository(language: String) =
        safeApiCall { apiService.getRepository(language) }

    suspend fun insertData(item: List<Item>) =
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            dao.insertData(item)
            Log.e("Success","Inserted data")
        }



    suspend fun getDbData() =
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            dao.getData()
        }


}