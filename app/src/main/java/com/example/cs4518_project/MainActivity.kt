package com.example.cs4518_project

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.FrameLayout


//view
const val scoreA = "scoreA.MESSAGE"
const val scoreB = "scoreB.MESSAGE"
const val teamAName = "TeamAName.MESSAGE"
const val teamBName = "TeamBName.MESSAGE"
const val REQUEST = 1 // The request code


class MainActivity : AppCompatActivity() {
    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val orientation = resources.configuration.orientation
        if (orientation == 1) {
            setContentView(R.layout.activity_main)
        } else {
            setContentView(R.layout.activity_landscape)
        }
        val currentFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (currentFragment == null) {
            val fragment = HistoryListFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }


        val target = intent.getStringExtra("TARGET_FRAGMENT")
        if (findViewById<FrameLayout>(R.id.framelayout) != null) {
            val fragmentTransaction = fragmentManager.beginTransaction()
            when (target) {
                "SCORE" -> fragmentTransaction.add(R.id.framelayout, ScoreFragment())
                "HISTORY" -> fragmentTransaction.add(R.id.framelayout, HistoryListFragment())
                else -> fragmentTransaction.add(R.id.framelayout, ScoreFragment())
            }
            Log.d(this::class.java.toString(), "Intent: $target")
            fragmentTransaction.commit()
        }

    }
}
