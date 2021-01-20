package com.appwiz.breakingbad.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appwiz.breakingbad.database.RoomDB
import com.appwiz.breakingbad.model.Character
import com.appwiz.breakingbad.model.EntityCharacter
import com.appwiz.breakingbad.repository.CharacterRepository
import com.appwiz.breakingbad.utils.NetworkState
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

class CharactersViewModel(application: Application): AndroidViewModel(application) {
    var characters: LiveData<List<EntityCharacter>>
    var state: MutableLiveData<NetworkState> = MutableLiveData()
    private val repository: CharacterRepository

    init {
        val dao = RoomDB.getDatabasenIstance(application).characterDao()
        repository = CharacterRepository(dao)
        characters = repository.allCharacters
    }

    fun loadData() {
        CoroutineScope(Dispatchers.IO).launch {
            val num = repository.check()
            if (num == 0) fetchData()
        }
    }

    private fun fetchData() {
        state.postValue(NetworkState.LOADING)
        val connection = URL("https://breakingbadapi.com/api/characters/")
            .openConnection() as HttpURLConnection
        val data = connection.inputStream.bufferedReader().readText()
        val gson = GsonBuilder().create()
        val chars = gson.fromJson(data, Array<Character>::class.java).toList()
        val entities: MutableList<EntityCharacter> = ArrayList()
        for (ch in chars) {
            val sb = StringBuilder()
            val appsb = StringBuilder()
            val bcssb = StringBuilder()
            ch.occupation.forEachIndexed { index, s ->
                sb.append(s)
                if (index != ch.occupation.size - 1) sb.append(", ")
            }
            try {
                ch.appearance.forEachIndexed { index, s ->
                    appsb.append(s)
                    if (index != ch.appearance.size - 1) appsb.append(", ")
                }
            } catch (e:Exception) {
                Log.e("crash", "error is ${e.message}")
            }
            ch.appearanceBCS.forEachIndexed { index, s ->
                bcssb.append(s)
                if (index != ch.appearanceBCS.size - 1) bcssb.append(", ")
            }
            val entity = EntityCharacter(ch.id, ch.name, ch.birthday, sb.toString(),
                ch.image, ch.status, ch.nickname, ch.portrayed, appsb.toString(),
                bcssb.toString(), ch.category)
            entities.add(entity)
        }
        CoroutineScope(Dispatchers.IO).launch { repository.insert(entities) }
        state.postValue(NetworkState.LOADED)
    }
}
