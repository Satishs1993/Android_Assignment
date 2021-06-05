package net.bg.satish_assignment.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import net.bg.satish_assignment.data.models.Item

@Database(entities = [Item::class],version = 2,exportSchema = false)
abstract class RepoDatabase : RoomDatabase(){

    abstract fun repoDao() : RepoDao
}