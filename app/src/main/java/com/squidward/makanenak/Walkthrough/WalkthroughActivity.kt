package com.squidward.makanenak.Walkthrough

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.squidward.makanenak.Login.LoginActivity
import com.squidward.makanenak.Utama.UtamaActivity
import com.squidward.makanenak.R
import kotlinx.android.synthetic.main.activity_walkthrough.*

class WalkthroughActivity : AppCompatActivity() {


    private lateinit var preference : SharedPreferences
    private val preferenceShowIntro = "Intro"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_walkthrough)

        preference = getSharedPreferences("IntroSlider", Context.MODE_PRIVATE)
        if (!preference.getBoolean(preferenceShowIntro, true)){
            Intent(applicationContext, LoginActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        pager.adapter = WalktroughAdapter()
        pager.offscreenPageLimit = 3
        dots.attachViewPager(pager)

        pager.onPageSelected {
            val colorRes = when (it) {
                0 -> R.color.white
                1 -> R.color.white
                2 -> R.color.white
                else -> R.color.white
            }
            val color = ContextCompat.getColor(this, colorRes)
            frame.setBackgroundColor(color)
            dots.setDotTintRes(if (color.isColorLight()) R.color.black else R.color.white)
        }

        val imgBtn = findViewById<ImageView>(R.id.img4)
        imgBtn.setOnClickListener {
            if (pager.currentItem + 1 < pager.childCount){
                pager.currentItem += 1
            } else {
                Intent(applicationContext, UtamaActivity::class.java).also {
                startActivity(it)
                finish()
                val editor = preference.edit()
                editor.putBoolean(preferenceShowIntro, false)
                editor.apply()
                } }
            }
    }
}
