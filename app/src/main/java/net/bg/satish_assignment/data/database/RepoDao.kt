package net.bg.satish_assignment.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import net.bg.satish_assignment.data.models.Item

@Dao
interface RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(data : List<Item>)

    @Query("Select * from Item")
    fun getData() : List<Item>
}