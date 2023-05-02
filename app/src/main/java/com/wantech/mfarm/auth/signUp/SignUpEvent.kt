package com.wantech.mfarm.auth.signUp

import com.wantech.mfarm.core.domain.model.Sacco

sealed class SignupEvent {
    data class EnteredUsername(val value: String) : SignupEvent()
    data class EnteredEmail(val value: String) : SignupEvent()
    data class EnteredPhoneNumber(val value: String) : SignupEvent()
    data class EnteredPassword(val value: String) : SignupEvent()
    data class SelectedSacco(val value: Sacco) : SignupEvent()
    object GetSaccos : SignupEvent()
    object TogglePasswordVisibility : SignupEvent()
    object Signup : SignupEvent()

}