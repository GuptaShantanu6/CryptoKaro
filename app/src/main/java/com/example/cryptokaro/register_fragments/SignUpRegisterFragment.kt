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
import android.widget.Toast
import com.example.cryptokaro.MainActivity
import com.example.cryptokaro.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignUpRegisterFragment : Fragment() {

    private lateinit var signUpBtn : View

    private lateinit var nameEditTextSignUp: EditText
    private lateinit var emailEditTextSignUp: EditText
    private lateinit var passwordEditTextSignUp: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sign_up__register_, container, false)

        signUpBtn = view.findViewById(R.id.register_signUp_view)

        nameEditTextSignUp = view.findViewById(R.id.nameEditTextSignUp)
        emailEditTextSignUp = view.findViewById(R.id.emailEditTextSignUp)
        passwordEditTextSignUp = view.findViewById(R.id.passwordEditTextSignUp)

        signUpBtn.setOnClickListener {
            hideKeyboard()
            createAccount(nameEditTextSignUp, emailEditTextSignUp, passwordEditTextSignUp)
        }

        return view

    }

    private fun createAccount(nameEditTextSignUp: EditText?, emailEditTextSignUp: EditText?, passwordEditTextSignUp: EditText?) {

        val nameText : String = nameEditTextSignUp?.text.toString()
        val emailText : String = emailEditTextSignUp?.text.toString()
        val passwordText : String = passwordEditTextSignUp?.text.toString()

        when {
            TextUtils.isEmpty(nameText) -> nameEditTextSignUp?.error = "Name cannot be empty"
            TextUtils.isEmpty(emailText) -> emailEditTextSignUp?.error = "Email cannot be empty"
            TextUtils.isEmpty(passwordText) -> passwordEditTextSignUp?.error = "Password cannot be empty"

            else ->{
                val progressDialog = ProgressDialog(activity)
                progressDialog.setTitle("Signing Up")
                progressDialog.setMessage("Please wait")
                progressDialog.setCancelable(false)
                progressDialog.setCanceledOnTouchOutside(false)
                progressDialog.show()

                val mAuth : FirebaseAuth = FirebaseAuth.getInstance()

                mAuth.createUserWithEmailAndPassword(emailText, passwordText)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) saveUserInfo(nameText, emailText, progressDialog)
                        else {
                            val message = task.exception.toString()
                            Toast.makeText(activity, "Error: $message", Toast.LENGTH_SHORT).show()
                            mAuth.signOut()
                            progressDialog.dismiss()
                        }
                    }

            }
        }

    }

    private fun saveUserInfo(nameText: String, emailText: String, progressDialog: ProgressDialog) {

        val currentUserId = FirebaseAuth.getInstance().currentUser!!.uid
        val usersRef = FirebaseDatabase.getInstance().reference.child("Users")

        val userMap = HashMap<String,Any>()
        userMap["uid"] = currentUserId
        userMap["name"] = nameText
        userMap["email"] = emailText
        userMap["profileImage"] = "https://firebasestorage.googleapis.com/v0/b/instaclone-138b8.appspot.com/o/Default%20Images%2Fman.png?alt=media&token=a3b6e6e6-8951-4120-81cc-ef64320b4778"

        usersRef.child(currentUserId).setValue(userMap)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    progressDialog.dismiss()
                    val intent = Intent(activity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
                else {
                    val message = task.exception.toString()
                    Toast.makeText(activity, "Error: $message", Toast.LENGTH_SHORT).show()
                    FirebaseAuth.getInstance().signOut()
                    progressDialog.dismiss()
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