package com.example.cryptokaro.register_fragments

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.cryptokaro.ForgotPasswordActivity
import com.example.cryptokaro.MainActivity
import com.example.cryptokaro.R
import com.google.firebase.auth.FirebaseAuth

class LogInRegisterFragment : Fragment() {

    private lateinit var logInBtn : View

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_log_in__register_, container, false)

        logInBtn = view.findViewById(R.id.register_login_view)
        emailEditText = view.findViewById(R.id.emailLogInRegisterEditText)
        passwordEditText = view.findViewById(R.id.passwordLogInRegisterEditText)

        logInBtn.setOnClickListener {
            hideKeyboard()
            logInAccount(emailEditText, passwordEditText)
        }

        val forgotPasscode : TextView = view.findViewById(R.id.forgotPasswordText)
        forgotPasscode.setOnClickListener {
            startActivity(Intent(activity, ForgotPasswordActivity::class.java))
        }

        return view
    }

    private fun logInAccount(emailEditText: EditText?, passwordEditText: EditText?) {
        val emailLogIn = emailEditText?.text.toString()
        val passwordLogIn = passwordEditText?.text.toString()

        when {
            TextUtils.isEmpty(emailLogIn) -> emailEditText?.error = "Email cannot be empty"
            TextUtils.isEmpty(passwordLogIn) -> passwordEditText?.error = "Password cannot be empty"

            else ->{
                val progressDialogBox = ProgressDialog(activity)
                progressDialogBox.setTitle("Logging In")
                progressDialogBox.setMessage("Please wait")
                progressDialogBox.show()
                progressDialogBox.setCancelable(false)
                progressDialogBox.setCanceledOnTouchOutside(false)

                val mAuth = FirebaseAuth.getInstance()

                mAuth.signInWithEmailAndPassword(emailLogIn, passwordLogIn)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            progressDialogBox.dismiss()
                            val intent = Intent(activity, MainActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        }
                        else {
                            val message = task.exception.toString()
                            Toast.makeText(activity, "Error: $message", Toast.LENGTH_SHORT).show()
                            FirebaseAuth.getInstance().signOut()
                            progressDialogBox.dismiss()
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