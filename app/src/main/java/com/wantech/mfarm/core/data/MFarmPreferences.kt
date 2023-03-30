package com.wantech.mfarm.core.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MFarmPreferences(private val dataStore: DataStore<Preferences>) {
    companion object {
        val ONBOARDED = booleanPreferencesKey("alreadyBoarded")
    }

    suspend fun saveOnBoard(onBoard: Boolean) {
        dataStore.edit { preference ->
            preference[ONBOARDED] = onBoard

        }
    }

   val isOnBoarded: Flow<Boolean> =
        dataStore.data.map { preference ->
            preference[ONBOARDED] ?: false
        }


}