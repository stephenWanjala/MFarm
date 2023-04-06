package com.wantech.mfarm.auth.signUp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.wantech.mfarm.auth.domain.repository.AuthRepository
import com.wantech.mfarm.auth.signUp.util.SignUpState
import com.wantech.mfarm.auth.signUp.util.SignUpUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val repository: AuthRepository) :
    ViewModel() {
    private val _state = mutableStateOf(SignUpUIState())
    val state: State<SignUpUIState> = _state

    private val _signUpUIState = MutableSharedFlow<SignUpState>()
    val signUpIState = _signUpUIState.asSharedFlow()


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