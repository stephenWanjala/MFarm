package com.wantech.mfarm.auth.signUp

sealed class SignupEvent {
    data class EnteredUsername(val value: String) : SignupEvent()
    data class EnteredEmail(val value: String) : SignupEvent()
    data class EnteredPassword(val value: String) : SignupEvent()
    object GetSaccos : SignupEvent()
    object TogglePasswordVisibility : SignupEvent()
    object Signup : SignupEvent()

}