package net.bg.satish_assignment.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.bg.satish_assignment.data.database.RepoDao
import net.bg.satish_assignment.data.database.RepoDatabase
import net.bg.satish_assignment.data.network.NetworkService
import net.bg.satish_assignment.data.network.services.GithubRepositoryService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGithubRepository(networkService: NetworkService) : GithubRepositoryService {
        return networkService.buildApiCall(GithubRepositoryService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext application: Context) : RepoDatabase {
        return Room.databaseBuilder(application, RepoDatabase::class.java,
            "RepoDatabase.db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    fun provideDbDao (repoDatabase: RepoDatabase) : RepoDao {
        return repoDatabase.repoDao()
    }
}