package com.appwiz.breakingbad.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.appwiz.breakingbad.model.EntityCharacter

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacters(chars: List<EntityCharacter>)

    @Query("select * from character_table")
    fun showCharacters(): LiveData<List<EntityCharacter>>

    @Query("select count(*) from character_table")
    suspend fun checkEmptyCharacters(): Int

    @Query("delete from character_table")
    fun removeCharacters()
}