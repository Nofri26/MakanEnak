package com.squidward.makanenak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.squidward.makanenak.Walkthrough.WalkthroughActivity

class Splashscreen : AppCompatActivity() {

    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        handler = Handler()
        handler.postDelayed({

            val intent = Intent(this, WalkthroughActivity::class.java)
            startActivity(intent)
            finish()

        }, 1500) //delay sebelum masuk
    }
}
