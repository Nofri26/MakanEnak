package com.squidward.makanenak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.squidward.makanenak.Tambah.TambahActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this, "Firebase Success", Toast.LENGTH_LONG).show()

        linearLayout.setOnClickListener {
            intent = Intent(this, TambahActivity::class.java)
            startActivity(intent)
        }
    }
}
