package net.bg.satish_assignment.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class NetworkService @Inject constructor() {

    companion object{
        private const val baseUrl = "https://api.github.com/"
    }

    fun <NetworkCall>  buildApiCall (apiCall : Class<NetworkCall>) : NetworkCall {

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getRetrofitClient())
            .build()
            .create(apiCall)
    }

    private fun getRetrofitClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .callTimeout(20L, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                chain.proceed(chain.request().newBuilder().also {
                    it.addHeader("Accept", "application/json")
                    it.addHeader("Content-Type", "application/json")
                }.build())
            }.also { client ->
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                client.addInterceptor(logging)
            }.build()
    }

}