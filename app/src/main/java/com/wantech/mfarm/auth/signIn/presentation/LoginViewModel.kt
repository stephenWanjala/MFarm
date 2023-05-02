package com.wantech.mfarm.auth.signIn.presentation

import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wantech.mfarm.auth.domain.repository.AuthRepository
import com.wantech.mfarm.auth.signIn.LoginEvent
import com.wantech.mfarm.auth.signIn.LoginState
import com.wantech.mfarm.auth.signIn.LoginUiState
import com.wantech.mfarm.core.domain.model.LoginRequest
import com.wantech.mfarm.core.util.Resource
import com.wantech.mfarm.onboarding.domain.model.repository.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor
    (
    private val repository: AuthRepository,
    private val userDataRepository: UserDataRepository
) : ViewModel() {
    private val _state = mutableStateOf(LoginUiState())
    val state: State<LoginUiState> = _state
    private val _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(),
        LoginState()
    )

    val accesToken = mutableStateOf("")


    init {
        
    }


    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EnteredEmail -> {
                if (!Patterns.EMAIL_ADDRESS.matcher(event.value).matches()) {
                    _state.value =
                        state.value.copy(isEmailError = LoginUiState.EmailError.InvalidEmail)
                } else {
                    _state.value = state.value.copy(isEmailError = null)

                }
                _state.value = state.value.copy(email = event.value)
            }

            is LoginEvent.EnteredPassword -> {
                if (event.value.length < 8) {
                    _state.value =
                        state.value.copy(isPasswordError = LoginUiState.PasswordError.InvalidPassword)
                } else {
                    _state.value = state.value.copy(isPasswordError = null)

                }
                _state.value = state.value.copy(password = event.value)
            }

            LoginEvent.TogglePasswordVisibility -> {
                _state.value = state.value.copy(isPasswordVisible = !state.value.isPasswordVisible)
            }

            LoginEvent.Login -> {
                loginFarmer()
            }

            is LoginEvent.SaveAuthenticatedStatus -> {
                viewModelScope.launch {
                    userDataRepository.saveAuthenticatedStatus(event.loginResponse)
                }
            }
        }
        _state.value =
            state.value.copy(isLoginButtonEnabled = (state.value.isPasswordError == null && state.value.isEmailError == null))

    }

    private fun loginFarmer() {
        viewModelScope.launch {
            repository.signInUserWithEmailAndPassword(
                LoginRequest(
                    email = state.value.email,
                    password = state.value.password
                )
            ).collectLatest { resposonse ->
                when (resposonse) {
                    is Resource.Error -> _loginState.value =
                        loginState.value.copy(error = resposonse.uiText)

                    is Resource.Loading -> _loginState.value =
                        loginState.value.copy(isLoading = true)

                    is Resource.Success -> {
                        resposonse.data?.let { data ->
                            accesToken.value = data.access
                            _loginState.value=loginState.value.copy(login = data)
                        }
                        _loginState.value = loginState.value.copy(isLoading = false)

                    }
                }
            }
        }
    }




}


