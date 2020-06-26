package com.squidward.makanenak.Walkthrough

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.aprp.whitespace.Walktrough.WalktroughAdapter
import com.aprp.whitespace.Walktrough.isColorLight
import com.aprp.whitespace.Walktrough.onPageSelected
import com.squidward.makanenak.MainActivity
import com.squidward.makanenak.R
import kotlinx.android.synthetic.main.activity_walkthrough.*

class WalkthroughActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_walkthrough)

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
        imgBtn.setOnClickListener {startActivity(Intent(this, MainActivity::class.java))}
    }
}
