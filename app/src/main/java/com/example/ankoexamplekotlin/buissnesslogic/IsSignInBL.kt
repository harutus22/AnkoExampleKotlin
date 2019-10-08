package com.example.ankoexamplekotlin.buissnesslogic

import com.example.ankoexamplekotlin.signin.model.AuthCredentials

interface IsSignInBL {
    fun checkUserCredentials(credentials: AuthCredentials): Boolean
}