package com.squidward.makanenak.Utama

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.*
import com.squidward.makanenak.R
import com.squidward.makanenak.Tambah.TambahActivity
import kotlinx.android.synthetic.main.activity_main.*

class UtamaActivity : AppCompatActivity() {

    lateinit var ref : DatabaseReference
    lateinit var list : MutableList<DataMakanan>
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this, "Firebase Success", Toast.LENGTH_LONG).show()

        linearLayout.setOnClickListener {
            intent = Intent(this, TambahActivity::class.java)
            startActivity(intent)
        }

        ref = FirebaseDatabase.getInstance().getReference("Makanan")
        list = mutableListOf()
        listView = findViewById(R.id.list_view)

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()){
                    for (h in p0.children){
                        val data = h.getValue(DataMakanan::class.java)
                        list.add(data!!)
                    }
                    val adapter = Adapter(applicationContext,R.layout.adapter_tampil,list)
                    listView.adapter = adapter
                }
            }
        })

    }
}
