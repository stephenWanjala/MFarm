package com.wantech.mfarm.auth.signUp.util

import com.wantech.mfarm.core.domain.model.RegisterResponse
import com.wantech.mfarm.core.domain.model.Sacco
import com.wantech.mfarm.core.util.UiText


data class SignUpUIState(
    val userName: String = "",
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isUserNameError: UsernameError? = null,
    val isEmailError: EmailError? = null,
    val isPasswordError: PasswordError? = null,
    val isNextButtonEnabled: Boolean = false,
    val dayOneOfPreviousPeriod: Int = 0,
    val periodLength: Int = 0,
    val isFinishEnabled: Boolean = false,
    val selectedSacco: Sacco?=null

) {
    sealed class UsernameError {
        object FieldEmpty : UsernameError()
        object InputTooShort : UsernameError()
    }

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

data class SignUpState(
    val isLoading: Boolean = false,
    val signUp: RegisterResponse? = null,
    val error: UiText? = null,
)
