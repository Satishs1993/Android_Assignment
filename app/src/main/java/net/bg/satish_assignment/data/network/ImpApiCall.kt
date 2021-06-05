package net.bg.satish_assignment.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.lang.NullPointerException

interface ImpApiCall {

    suspend fun<T> safeApiCall (
        apiCall : suspend () -> T
    ) : RetrofitCallbackResource<T>{

        return withContext(Dispatchers.IO){
            try {
                RetrofitCallbackResource.Success(apiCall.invoke())
            }catch (throwable : Throwable){
                when(throwable){
                    is HttpException ->{
                        RetrofitCallbackResource.Error(throwable.code(),false,"",throwable.response()?.errorBody())

                    }
                    else -> {
                        RetrofitCallbackResource.Error(null,true,throwable.message.toString(),null)

                    }
                }
            }
        }
    }

}