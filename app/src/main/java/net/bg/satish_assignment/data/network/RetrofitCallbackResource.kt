package net.bg.satish_assignment.data.network

import okhttp3.ResponseBody

sealed class RetrofitCallbackResource <out T>{
    data class Success<out T>(val value : T) : RetrofitCallbackResource<T> ()
    data class Error(
        val errorCode : Int?,
        val isNetworkError : Boolean,
        val error : String,
        val errorMessage : ResponseBody?
    ): RetrofitCallbackResource<Nothing>()
}
