package net.bg.satish_assignment.data.network.services

import net.bg.satish_assignment.data.models.GithubResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubRepositoryService {

    @GET("search/repositories")
    suspend fun getRepository(@Query("q") q : String) : GithubResponse
}