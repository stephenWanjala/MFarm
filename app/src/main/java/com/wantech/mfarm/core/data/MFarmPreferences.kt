package com.wantech.mfarm.core.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.wantech.mfarm.core.domain.model.LoginResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MFarmPreferences(private val dataStore: DataStore<Preferences>) {
    companion object {
        val ONBOARDED = booleanPreferencesKey("alreadyBoarded")
        val MFARMPREFERENCES = "mFarm_Preferences"
        val isAuthenticated = booleanPreferencesKey("isAuthenticated")
        val ACCESSTOKEN = stringPreferencesKey("accessToken")
        val REFRESHTOKEN = stringPreferencesKey("refreshToken")
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

    suspend fun saveAuthenticatedStatus(loginResponse: LoginResponse) {
        dataStore.edit { preference ->
           preference[isAuthenticated] = true
            preference[ACCESSTOKEN] = loginResponse.accessToken
            preference[REFRESHTOKEN] = loginResponse.refreshToken

        }
    }


}