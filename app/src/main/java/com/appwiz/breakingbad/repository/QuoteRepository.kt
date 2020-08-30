package com.appwiz.breakingbad.repository

import androidx.lifecycle.LiveData
import com.appwiz.breakingbad.database.QuoteDao
import com.appwiz.breakingbad.model.Quote

class QuoteRepository(private val quoteDao:QuoteDao) {

    val quotelist: LiveData<List<Quote>> = quoteDao.showQuotes()

    suspend fun insert(quotes:List<Quote>) {
        quoteDao.addQuotes(quotes)
    }

    suspend fun check() = quoteDao.checkEmptyQuoteList()

}