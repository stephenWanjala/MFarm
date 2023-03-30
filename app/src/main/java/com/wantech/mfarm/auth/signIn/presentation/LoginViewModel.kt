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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor
    (private val repository: AuthRepository) : ViewModel() {
    private val _state = mutableStateOf(LoginUiState())
    val state: State<LoginUiState> = _state
    private val _loginState = MutableStateFlow(LoginState())
    val loginState: SharedFlow<LoginState> = _loginState.asSharedFlow()
    private val passwordRegex =
        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,10}$"


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
                if (!Pattern.matches(passwordRegex, event.value)) {
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

                login(email = state.value.email, password = state.value.password)
            }
        }
        _state.value =
            state.value.copy(isLoginButtonEnabled = (state.value.isPasswordError == null && state.value.isEmailError == null))

    }

    private fun login(email: String, password: String) {
        viewModelScope.launch {
            repository.signInUserWithEmailAndPassword(LoginRequest(email, password))
                .onEach { resource ->
                    when (resource) {
                        is Resource.Error -> {
                            _loginState.emit(LoginState(error = resource.uiText))
                        }
                        is Resource.Loading -> {
                            _loginState.emit(LoginState(isLoading = true))
                        }
                        is Resource.Success -> {
                            _loginState.emit(LoginState(login = resource.data))
                        }
                    }

                }
        }
    }

}