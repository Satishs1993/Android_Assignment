package net.bg.satish_assignment

import android.app.Application
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.*
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.bg.satish_assignment.data.ReccuringDataWorker
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class ApplicationClass : Application() , Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun onCreate() {
        super.onCreate()
        intTask()

    }

    private fun intTask (){
        CoroutineScope(Dispatchers.IO).launch {
            setupRecurringWork()
        }
    }

    private fun setupRecurringWork() {

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val repeatingRequest = PeriodicWorkRequestBuilder<ReccuringDataWorker>(15,
            TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(applicationContext)
            .enqueueUniquePeriodicWork(ReccuringDataWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest)


    }

    override fun getWorkManagerConfiguration() =
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .setMinimumLoggingLevel(Log.ERROR)
            .build()

}