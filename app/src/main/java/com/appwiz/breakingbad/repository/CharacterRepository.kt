package com.appwiz.breakingbad.repository

import androidx.lifecycle.LiveData
import com.appwiz.breakingbad.database.CharacterDao
import com.appwiz.breakingbad.model.EntityCharacter

class CharacterRepository(private val dao: CharacterDao) {

    val allCharacters: LiveData<List<EntityCharacter>> = dao.showCharacters()

    suspend fun insert(chars:List<EntityCharacter>) {
        dao.addCharacters(chars)
    }

    suspend fun check() = dao.checkEmptyCharacters()

}