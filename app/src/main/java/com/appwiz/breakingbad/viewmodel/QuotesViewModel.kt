package com.appwiz.breakingbad.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appwiz.breakingbad.database.RoomDB
import com.appwiz.breakingbad.model.Quote
import com.appwiz.breakingbad.repository.QuoteRepository
import com.appwiz.breakingbad.utils.NetworkState
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.URL

class QuotesViewModel(application: Application): AndroidViewModel(application) {

    var quotes: LiveData<List<Quote>>
    var state: MutableLiveData<NetworkState> = MutableLiveData()
    private val repository: QuoteRepository

    init {
        val dao = RoomDB.getDatabasenIstance(application).quoteDao()
        repository = QuoteRepository(dao)
        quotes = repository.quotelist
    }

    fun loadData() {
        CoroutineScope(Dispatchers.IO).launch {
            val num = repository.check()
            if (num == 0) fetchData()
        }
    }

    private fun fetchData() {
        state.postValue(NetworkState.LOADING)
        val connection = URL("https://breakingbadapi.com/api/quotes/")
            .openConnection() as HttpURLConnection
        val data = connection.inputStream.bufferedReader().readText()
        val gson = GsonBuilder().create()
        val res = gson.fromJson(data, Array<Quote>::class.java).toList()
        CoroutineScope(Dispatchers.IO).launch { repository.insert(res) }
        state.postValue(NetworkState.LOADED)
    }
}
