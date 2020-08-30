package com.appwiz.breakingbad.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.appwiz.breakingbad.model.Death

@Dao
interface DeathDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDeaths(deaths: List<Death>)

    @Query("select * from death_table")
    fun showDeaths(): LiveData<List<Death>>

    @Query("select count(*) from death_table")
    suspend fun checkEmptyDeathList(): Int

    @Query("delete from death_table")
    fun removeDeaths()
}
