package com.appwiz.breakingbad.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.appwiz.breakingbad.model.Quote

@Dao
interface QuoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuotes(deaths: List<Quote>)

    @Query("select * from quotes_table")
    fun showQuotes(): LiveData<List<Quote>>

    @Query("select count(*) from quotes_table")
    suspend fun checkEmptyQuoteList(): Int

    @Query("delete from quotes_table")
    fun removeQuotes()
}
