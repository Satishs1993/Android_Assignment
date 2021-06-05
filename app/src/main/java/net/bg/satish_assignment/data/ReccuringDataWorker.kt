package net.bg.satish_assignment.data

import android.content.Context
import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.bg.satish_assignment.data.models.GithubResponse
import net.bg.satish_assignment.data.network.RetrofitCallbackResource
import net.bg.satish_assignment.data.repository.GithubRepository

class ReccuringDataWorker @WorkerInject constructor (
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    private val repo : GithubRepository) :
        CoroutineWorker(appContext, params) {

        companion object {
        const val WORK_NAME = "net.bg.satish_assignment.data.RefreshDataWorker"
    }
    override suspend fun doWork(): Result {

        GlobalScope.launch(Dispatchers.Main) {

            callApi().observeForever {
                if (it != null) {
                    when(it){
                        is RetrofitCallbackResource.Error ->
                            Log.e("Error", "Empty Data")
                        is RetrofitCallbackResource.Success -> {
                            var data = it.value.items
                            if (data!=null){
                                CoroutineScope(Dispatchers.IO).launch {
                                    repo.insertData(data)
                                }
                            }
                        }
                    }
                }
            }
        }
        return Result.success()
    }

    private fun callApi () : LiveData<RetrofitCallbackResource<GithubResponse>> {
        var data : MutableLiveData<RetrofitCallbackResource<GithubResponse>> =
            MutableLiveData()
        CoroutineScope(Dispatchers.IO).launch {

            data.postValue(repo.getRepository("android"))

        }
        return data
    }

}