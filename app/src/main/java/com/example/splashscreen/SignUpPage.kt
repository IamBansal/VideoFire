package com.example.splashscreen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignUpPage : AppCompatActivity() {

    private var email: EditText? = null
    private var password: EditText? = null
    private var confirmPassword: EditText? = null
    private var signupButton: Button? = null
    private var firebaseAuth: FirebaseAuth? = null
    private var user: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_page)

        email = findViewById(R.id.emailSign)
        password = findViewById(R.id.passwordSign)
        confirmPassword = findViewById(R.id.passwordConfirm)
        signupButton = findViewById(R.id.btnSign)
        firebaseAuth = FirebaseAuth.getInstance()
        user = firebaseAuth?.currentUser!!

        signupButton?.setOnClickListener {
            signUp()
        }

    }

    @SuppressLint("SetTextI18n")
    private fun signUp() {

        val emailText = email?.text.toString().trim()
        val passText = password?.text.toString().trim()
        val confirmPassText = confirmPassword?.text.toString().trim()

        if (TextUtils.isEmpty(emailText) || TextUtils.isEmpty(passText) || confirmPassText != passText) {
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Signup Error")
                .setMessage("Fields can't be empty or password didn't matched.\nFill and check all credentials.")
                .setPositiveButton("Okay") { _, _ -> }
                .create()
                .show()
        } else {
            firebaseAuth?.createUserWithEmailAndPassword(emailText, passText)
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        signupButton?.text = "Signing you up..."
                        user?.sendEmailVerification()
                        startActivity(Intent(this, LoginPage::class.java))
                        signupButton?.text = "Sign up"
                    } else {
                        val dialog = AlertDialog.Builder(this)
                        dialog.setTitle("Signup Error")
                            .setMessage(task.exception?.message)
                            .setPositiveButton("Okay") { _, _ -> }
                            .create()
                            .show()
                    }
                }
        }


    }
}