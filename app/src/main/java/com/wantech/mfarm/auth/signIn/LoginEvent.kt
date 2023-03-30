package com.wantech.mfarm.auth.signIn

import com.wantech.mfarm.core.domain.model.LoginResponse
import com.wantech.mfarm.core.util.UiText

sealed class LoginEvent {
    data class EnteredEmail(val value: String) : LoginEvent()
    data class EnteredPassword(val value: String) : LoginEvent()
    object TogglePasswordVisibility : LoginEvent()
    object Login : LoginEvent()
}



data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isEmailError: EmailError? = null,
    val isPasswordError: PasswordError? = null,
    val isLoginButtonEnabled: Boolean = false,
) {
    sealed class EmailError {
        object FieldEmpty : EmailError()
        object InvalidEmail : EmailError()
    }

    sealed class PasswordError {
        object FieldEmpty : PasswordError()
        object InvalidPassword : PasswordError()
        object InputTooShort : PasswordError()
    }
}

data class LoginState(
    val isLoading: Boolean = false,
    val login: LoginResponse? = null,
    val error: UiText? = null
)
