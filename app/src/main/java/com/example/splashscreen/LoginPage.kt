package com.example.splashscreen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

class LoginPage : AppCompatActivity() {

    private var email: EditText? = null
    private var password: EditText? = null
    private var loginButton: Button? = null
    private var forgot: TextView? = null
    private var signUp: TextView? = null
    private var signGoogle: TextView? = null
    private var firebaseAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        email = findViewById(R.id.emailLogin)
        password = findViewById(R.id.passwordLogin)
        loginButton = findViewById(R.id.btnLogin)
        forgot = findViewById(R.id.tvForgot)
        signUp = findViewById(R.id.tvSign)
        signGoogle = findViewById(R.id.tvSignGoogle)
        firebaseAuth = FirebaseAuth.getInstance()

        loginButton?.setOnClickListener {
            login()
        }

        forgot?.setOnClickListener {
            resetPassword()
        }

        signUp?.setOnClickListener {
            startActivity(Intent(this, SignUpPage::class.java))
        }

        signGoogle?.setOnClickListener {
            signWithGoogle()
        }

    }

    @SuppressLint("SetTextI18n")
    private fun login() {

        val emailText = email?.text.toString().trim()
        val passText = password?.text.toString().trim()

        if (TextUtils.isEmpty(emailText) || TextUtils.isEmpty(passText)) {
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Login Error")
                .setMessage("Fields can't be empty.\nFill all credentials.")
                .setPositiveButton("Okay") { _, _ -> }
                .create()
                .show()
        } else {
            loginButton?.text = "Logging in..."

            firebaseAuth?.signInWithEmailAndPassword(emailText, passText)
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        if (firebaseAuth?.currentUser!!.isEmailVerified) {
                            startActivity(Intent(this, AppMain::class.java))
                            email?.text?.clear()
                            password?.text?.clear()
                            loginButton?.text = "Log in"
                        } else {
                            val dialog = AlertDialog.Builder(this)
                            dialog.setTitle("Login Error")
                                .setMessage("Email not verified.")
                                .setPositiveButton("Okay") { _, _ -> }
                                .create()
                                .show()
                        }
                    } else {
                        val dialog = AlertDialog.Builder(this)
                        dialog.setTitle("Login Error")
                            .setMessage(task.exception?.message)
                            .setPositiveButton("Okay") { _, _ -> }
                            .create()
                            .show()
                    }
                }
        }

    }

    private fun resetPassword() {
        val emailText = email?.text.toString().trim()

        if (TextUtils.isEmpty(emailText) || firebaseAuth?.currentUser!!.isEmailVerified) {
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Reset Error")
                .setMessage("Email field can't be empty for resetting.\nOr email is not verified.")
                .setPositiveButton("Okay") { _, _ -> }
                .create()
                .show()
        } else {
            firebaseAuth?.sendPasswordResetEmail(emailText)?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val dialog = AlertDialog.Builder(this)
                    dialog.setTitle("Reset Password")
                        .setMessage("Request for password reset accepted.\nCheck email.")
                        .setPositiveButton("Okay") { _, _ -> }
                        .create()
                        .show()
                } else {
                    val dialog = AlertDialog.Builder(this)
                    dialog.setTitle("Reset Error")
                        .setMessage(task.exception?.message)
                        .setPositiveButton("Okay") { _, _ -> }
                        .create()
                        .show()
                }

            }
        }

    }

    private fun signWithGoogle() {

    }

    override fun onBackPressed() {
        finishAffinity()
    }

}