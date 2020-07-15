package com.squidward.makanenak.Tambah

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.squidward.makanenak.R
import kotlinx.android.synthetic.main.activity_tambah.*
import java.util.*

class TambahActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah)

        btn_tambah.setOnClickListener {
            confirmAdd()
        }

        btn_image.setOnClickListener {
            Log.d("TambahActivity", "try to show photo")

            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }
    }

    private fun confirmAdd() {
        uploadImage()
    }

    var selectedPhotoUri : Uri? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            Log.d("TambahActivity", "Photo was selected")

            selectedPhotoUri = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)

            val bitmapDrawable = BitmapDrawable(bitmap)
            btn_image.setBackgroundDrawable(bitmapDrawable)
        }
    }

    private fun uploadImage(){
        if(selectedPhotoUri == null) return
        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/game_image/$filename")

        ref.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                Log.d("UploadImage","Upload Success: ${it.metadata?.path}")
                ref.downloadUrl.addOnSuccessListener {
                    Log.d("UploadImage","File Location: $it")
                    saveToDatabase(it.toString())
                }
            }
    }

    private fun saveToDatabase(gambar_makanan: String) {
        val nama_makanan = et_nama_makanan.text.toString()
        val harga_makanan = et_harga.text.toString()
        val review_makanan = et_review.text.toString()

        val ref = FirebaseDatabase.getInstance().getReference("/Makanan/$nama_makanan")

        val dataMakanan = DataMakanan(nama_makanan, harga_makanan, review_makanan, gambar_makanan)
        ref.setValue(dataMakanan)
            .addOnSuccessListener {
                Toast.makeText(this,"Makanan successfuly added to library!", Toast.LENGTH_LONG).show()
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }
    }
}

class DataMakanan(val nama_makanan: String, val harga_makanan: String,
               val review_makanan: String, val gambar_makanan: String)