package com.wantech.mfarm.onboarding.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wantech.mfarm.onboarding.domain.model.repository.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDataVieModel @Inject constructor(
    private val userDataRepository: UserDataRepository
): ViewModel(){


      fun updateOnBoarding(isOnBoarded:Boolean){
          viewModelScope.launch {
              userDataRepository.onBoard(isOnBoarded = isOnBoarded)
          }
      }
}