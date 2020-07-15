package com.squidward.makanenak.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.squidward.makanenak.MainActivity
import com.squidward.makanenak.R
import com.squidward.makanenak.Register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        btn_login.setOnClickListener {

            val email = email_login.text.toString()
            val password = password_login.text.toString()
            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(this,"Registrasi Dulu", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener {
                    if (!it.isSuccessful){
                        startActivity(Intent(this, LoginActivity::class.java))
                        return@addOnCompleteListener
                    } else
                        Toast.makeText(this,"Selamat Datang !", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                }
                .addOnFailureListener {
                    Log.d("Main","Failed Login: ${it.message}")
                    Toast.makeText(this,"Admin Area !", Toast.LENGTH_SHORT).show()
                }
        }

        btn_register.setOnClickListener {
            // Handler code here.
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
