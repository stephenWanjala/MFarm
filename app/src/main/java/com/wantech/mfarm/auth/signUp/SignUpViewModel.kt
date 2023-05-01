package com.wantech.mfarm.auth.signUp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wantech.mfarm.auth.domain.repository.AuthRepository
import com.wantech.mfarm.auth.domain.repository.LocationAndSaccoDetails
import com.wantech.mfarm.auth.signUp.util.SignUpState
import com.wantech.mfarm.auth.signUp.util.SignUpUIState
import com.wantech.mfarm.core.domain.model.Sacco
import com.wantech.mfarm.core.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val saccoRepository: LocationAndSaccoDetails
) :
    ViewModel() {
    private val _state = mutableStateOf(SignUpUIState())
    val state: State<SignUpUIState> = _state

    private val _signUpUIState = MutableStateFlow(SignUpState())
    val signUpIState = _signUpUIState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = SignUpState()
    )
    private val _saccos = MutableStateFlow(listOf<Sacco>())
     var saccos =_saccos.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())


    init {

       getSacosInLocation()
    }

    private fun getSacosInLocation() {
        viewModelScope.launch {
            saccoRepository.getSaccoByLocation("embu").collectLatest { resource->
                when(resource){
                    is Resource.Error -> _signUpUIState.update { it.copy(error = resource.uiText) }
                    is Resource.Loading -> _signUpUIState.update { it.copy(isLoading = true) }
                    is Resource.Success -> {
                        resource.data?.let { list->
                            _saccos.update { list }
                        }
                    }
                }
            }
        }
    }


    fun onEvent(event: SignupEvent) = when (event) {
        is SignupEvent.EnteredEmail -> {
            _state.value = state.value.copy(email = event.value)
        }

        is SignupEvent.EnteredPassword -> {
            _state.value = state.value.copy(password = event.value)
        }

        is SignupEvent.EnteredUsername -> {
            _state.value = state.value.copy(userName = event.value)
        }

        SignupEvent.Signup -> {
//            signUp(
//                userName = state.value.userName.trim(),
//                email = state.value.email.trim(),
//                password = state.value.password.trim()
//            )

        }

        SignupEvent.TogglePasswordVisibility -> {
            _state.value = state.value.copy(isPasswordVisible = !state.value.isPasswordVisible)
        }

        SignupEvent.GetSaccos -> getSacosInLocation()
        is SignupEvent.SelectedSacco -> {
            _state.value =state.value.copy(selectedSacco = event.value)
        }
    }


    private fun signUp(userName: String, email: String, password: String, phone: String) {
        /* viewModelScope.launch {
             repository.createUserWithEmailAndPassword(
                 registerRequest = RegisterRequest(
                     name = userName,
                     email = email,
                     phone = phone,
                     password = password,

                 )
             ).onEach { resource ->
                 when (resource) {
                     is Resource.Error -> {
                         _signUpUIState.emit(SignUpState(error = resource.uiText))
                     }
                     is Resource.Loading -> {
                         _signUpUIState.emit(SignUpState(isLoading = true))
                     }
                     is Resource.Success -> {
                         _signUpUIState.emit(SignUpState(signUp = resource.data))
                     }
                 }
             }
         }*/
    }
}