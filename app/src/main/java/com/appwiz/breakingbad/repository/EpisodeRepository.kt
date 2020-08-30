package com.appwiz.breakingbad.repository

import androidx.lifecycle.LiveData
import com.appwiz.breakingbad.database.EpisodeDao
import com.appwiz.breakingbad.model.EntityEpisode

class EpisodeRepository(private val dao: EpisodeDao) {

    val allEpisode: LiveData<List<EntityEpisode>> = dao.showEpisodes()

    suspend fun insert(epis:List<EntityEpisode>) {
        dao.addEpisodes(epis)
    }

    suspend fun check() = dao.checkEmptyEpisodes()

}