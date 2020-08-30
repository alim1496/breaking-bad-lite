package com.appwiz.breakingbad.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appwiz.breakingbad.database.RoomDB
import com.appwiz.breakingbad.model.EntityEpisode
import com.appwiz.breakingbad.model.Episode
import com.appwiz.breakingbad.repository.EpisodeRepository
import com.appwiz.breakingbad.utils.NetworkState
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

class EpisodesViewModel(application: Application): AndroidViewModel(application) {
    var episodes: LiveData<List<EntityEpisode>>
    var state: MutableLiveData<NetworkState> = MutableLiveData()
    private val repository: EpisodeRepository

    init {
        val dao = RoomDB.getDatabasenIstance(application).episodeDao()
        repository = EpisodeRepository(dao)
        episodes = repository.allEpisode
    }

    fun loadData() {
        CoroutineScope(Dispatchers.IO).launch {
            val num = repository.check()
            if (num == 0) fetchData()
        }
    }

    private fun fetchData() {
        state.postValue(NetworkState.LOADING)
        val connection = URL("https://breakingbadapi.com/api/episodes/")
            .openConnection() as HttpURLConnection
        val data = connection.inputStream.bufferedReader().readText()
        val gson = GsonBuilder().create()
        val epis = gson.fromJson(data, Array<Episode>::class.java).toList()
        val entities: MutableList<EntityEpisode> = ArrayList()
        for (epi in epis) {
            val sb = StringBuilder()
            epi.characters.forEachIndexed { index, s ->
                sb.append(s)
                if (index != epi.characters.size - 1) sb.append(", ")
            }
            val entity = EntityEpisode(epi.id, epi.title, epi.season,
                sb.toString(), epi.date, epi.episode, epi.series)
            entities.add(entity)
        }
        CoroutineScope(Dispatchers.IO).launch { repository.insert(entities) }
        state.postValue(NetworkState.LOADED)
    }
}