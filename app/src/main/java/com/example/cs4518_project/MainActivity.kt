package com.example.cs4518_project

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.FrameLayout
import androidx.lifecycle.ViewModelProvider


const val REQUEST = 1 // The request code


class MainActivity : AppCompatActivity() {
    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val orientation = resources.configuration.orientation
        if (orientation == 1) {
            setContentView(R.layout.activity_main)
        } else {
            setContentView(R.layout.activity_main_land)
        }
//        val currentFragment =
//            supportFragmentManager.findFragmentById(R.id.fragment_container)
//
//        if (currentFragment == null) {
//            val fragment = HistoryListFragment.newInstance()
//            supportFragmentManager
//                .beginTransaction()
//                .add(R.id.fragment_container, fragment)
//                .commit()
//        }

        val target = intent.getStringExtra("TARGET_FRAGMENT")
        if (findViewById<FrameLayout>(R.id.frameLayout) != null) {
            val fragmentTransaction = fragmentManager.beginTransaction()
            when (target) {
                "SCORE" -> fragmentTransaction.add(R.id.frameLayout, ScoreFragment())
                "HISTORY" -> fragmentTransaction.add(R.id.frameLayout, HistoryListFragment())
                "DETAIL" -> fragmentTransaction.add(R.id.frameLayout, DetailFragment())
                else -> fragmentTransaction.add(R.id.frameLayout, ScoreFragment())
            }
            Log.d(this::class.java.toString(), "Intent: $target")
            fragmentTransaction.commit()
        }
    }
}
