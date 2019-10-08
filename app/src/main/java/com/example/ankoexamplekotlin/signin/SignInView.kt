package com.example.ankoexamplekotlin.signin

import android.os.Build
import android.view.Gravity
import android.widget.EditText
import androidx.core.content.ContextCompat
import com.example.ankoexamplekotlin.R
import com.example.ankoexamplekotlin.SignInActivity
import org.jetbrains.anko.*

class SignInView : AnkoComponent<SignInActivity> {

    private lateinit var ankoContext: AnkoContext<SignInActivity>

    override fun createView(ui: AnkoContext<SignInActivity>) = with(ui) {
        ankoContext = ui

        verticalLayout {
            this.gravity = Gravity.CENTER

            scrollView {

                verticalLayout {

                    verticalLayout {
                        id = R.id.formLogin
                        gravity = Gravity.CENTER
                        padding = dip(20)

                        lparams(width = dip(300), height = matchParent) {
                            this.gravity = Gravity.CENTER
                            // API >= 16
                            configuration(fromSdk = Build.VERSION_CODES.JELLY_BEAN) {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                    background = ContextCompat.getDrawable(
                                        ctx, android.R.color.white)
                                }
                            }
                            clipToPadding = false
                            bottomMargin = dip(16)
                        }

                        val username = editText {
                            id = R.id.usernameEditText
                            hintResource = R.string.sign_in_username

                        }.lparams(width = matchParent, height = wrapContent)

                        val password = editText {
                            id = R.id.passwordEditText
                            hintResource = R.string.sign_in_password

                        }.lparams(width = matchParent, height = wrapContent)

                        button {
                            id = R.id.signInButton
                            textResource = R.string.sign_in_button

                            onClick {
                                handleOnSignInButtonPressed(username = username.text.toString(), password = password.text.toString())
                            }

                        }.lparams(width = matchParent, height = wrapContent)

                    }.applyRecursively { view ->
                        when (view) {
                            is EditText -> view.textSize = 24f
                        }
                    }

                }.lparams(width = matchParent, height = matchParent)

            }.lparams(width = matchParent, height = wrapContent)

        }
    }

    private fun handleOnSignInButtonPressed(username: String, password: String) {
        with(ankoContext) {
            if (username.isBlank() or password.isBlank()) {
                alert(title = R.string.signIn_alert_invalid_user_title,
                    message = R.string.signIn_alert_invalid_user_message) {

                    positiveButton(R.string.dialog_button_close) {}
                }.show()
            } else {
                owner.authorizeUser(username, password)
            }
        }
    }

    fun showAccessDeniedAlertDialog() {
        with(ankoContext) {
            alert(title = R.string.signIn_alert_access_denied_title,
                message = R.string.signIn_alert_access_denied_msg) {

                positiveButton(R.string.dialog_button_close) {}
            }.show()
        }
    }

}