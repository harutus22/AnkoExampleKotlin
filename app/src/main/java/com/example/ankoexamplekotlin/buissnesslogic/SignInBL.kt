package com.example.ankoexamplekotlin.buissnesslogic

import com.example.ankoexamplekotlin.signin.model.AuthCredentials

class SignInBL:IsSignInBL {
    override fun checkUserCredentials(credentials: AuthCredentials): Boolean {
        return "frosty".equals(credentials.userName) && "snowman".equals(credentials.password)
    }
}