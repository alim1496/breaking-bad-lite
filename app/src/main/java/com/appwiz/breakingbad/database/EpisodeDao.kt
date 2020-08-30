package com.appwiz.breakingbad.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.appwiz.breakingbad.model.EntityEpisode

@Dao
interface EpisodeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEpisodes(episodes: List<EntityEpisode>)

    @Query("select * from episode_table")
    fun showEpisodes(): LiveData<List<EntityEpisode>>

    @Query("select count(*) from episode_table")
    suspend fun checkEmptyEpisodes(): Int

    @Query("delete from episode_table")
    fun removeEpisodes()
}