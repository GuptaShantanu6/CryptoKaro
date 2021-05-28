package com.example.cryptokaro

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        supportActionBar?.hide()

        val emailResetBtn : View = findViewById(R.id.emailResetView)
        emailResetBtn.setOnClickListener {

            val emailField : EditText = findViewById(R.id.emailResetField)
            val emailText = emailField.text.toString()

            if (emailText.isEmpty()) {
                Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_SHORT).show()
            }
            else {
                hideKeyboard()

                val progressDialogBox = ProgressDialog(this)
                progressDialogBox.setTitle("Sending the Email")
                progressDialogBox.setMessage("Please wait")
                progressDialogBox.show()
                progressDialogBox.setCancelable(false)
                progressDialogBox.setCanceledOnTouchOutside(false)

                Firebase.auth.sendPasswordResetEmail(emailText)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            progressDialogBox.dismiss()
                            Toast.makeText(this, "Email has been sent. Please check your email for further instructions", Toast.LENGTH_SHORT).show()
                        }
                        else {
                            val message = task.exception.toString()
                            progressDialogBox.dismiss()
                            Toast.makeText(this, "Error: $message", Toast.LENGTH_SHORT).show()
                        }
                    }
            }

        }


    }

    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}