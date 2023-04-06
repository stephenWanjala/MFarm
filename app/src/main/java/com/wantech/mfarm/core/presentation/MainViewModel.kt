package com.wantech.mfarm.core.presentation

import androidx.lifecycle.ViewModel
import com.wantech.mfarm.onboarding.domain.model.repository.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(userDataRepository: UserDataRepository) :
    ViewModel() {
        val isOnBoarded =userDataRepository.userOnBoarded
    }