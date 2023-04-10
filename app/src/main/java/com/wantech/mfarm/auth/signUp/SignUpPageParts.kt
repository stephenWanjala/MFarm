package com.wantech.mfarm.auth.signUp

sealed class SignUpPageParts(private val name: String,private val page:Int) {
    object LocationAndSacco : SignUpPageParts("Location & Sacco", page = 0)
    object PersonalDetails : SignUpPageParts("Personal Details", page = 1)
    object AuthCredentials : SignUpPageParts("credentials", page = 2)
    override fun toString(): String = name
}
