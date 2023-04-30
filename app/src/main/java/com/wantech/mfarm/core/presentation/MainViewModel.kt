package com.wantech.mfarm.core.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wantech.mfarm.core.util.Screen
import com.wantech.mfarm.onboarding.domain.model.repository.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val userDataRepository: UserDataRepository) :
    ViewModel() {



    private val _startDestination = mutableStateOf(Screen.OnBoarding.route)
    val startDestination: State<String> = _startDestination

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            userDataRepository.userOnBoarded.collect { completed ->
                if (completed) _startDestination.value =
                    Screen.SignIn.route else _startDestination.value = Screen.OnBoarding.route
            }
           delay(3000)
            _isLoading.update { false }
        }
    }

}