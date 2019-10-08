package com.example.ankoexamplekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ankoexamplekotlin.buissnesslogic.IsSignInBL
import com.example.ankoexamplekotlin.buissnesslogic.SignInBL
import com.example.ankoexamplekotlin.signin.SignInView

import com.example.ankoexamplekotlin.signin.model.AuthCredentials
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.toast

class SignInActivity : AppCompatActivity() {
    private val signInBL: IsSignInBL = SignInBL()
    private lateinit var view: SignInView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = SignInView()
        view.setContentView(this)
    }

    fun authorizeUser(userName: String, password: String){
        doAsync {
            val authorized = signInBL.checkUserCredentials(AuthCredentials(userName, password))
            activityUiThread {
                if(authorized) toast("Signed") else view.showAccessDeniedAlertDialog()
            }
        }
    }
}
