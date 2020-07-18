package com.squidward.makanenak.Utama

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.squidward.makanenak.R


class Adapter (val mCtx: Context, val layoutResId: Int, val list: List<DataMakanan> )
    : ArrayAdapter<DataMakanan>(mCtx,layoutResId,list){

    override fun getView(position: Int, convertView: android.view.View?, parent: ViewGroup): android.view.View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: android.view.View = layoutInflater.inflate(layoutResId,null)

        val list_nama = view.findViewById<TextView>(R.id.list_nama)
        val list_harga = view.findViewById<TextView>(R.id.list_harga)
        val list_review = view.findViewById<TextView>(R.id.list_review)
        val list_gambar = view.findViewById<ImageView>(R.id.list_gambar)

        val makanan = list[position]

        list_nama.text = makanan.nama_makanan
        list_harga.text = makanan.harga_makanan
        list_review.text = makanan.review_makanan
        Picasso.get().load(makanan.gambar_makanan).into(list_gambar)

        return view

    }
}