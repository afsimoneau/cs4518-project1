package com.example.cs4518_project

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import android.content.Intent
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


//view
const val scoreA = "scoreA.MESSAGE"
const val scoreB = "scoreB.MESSAGE"
const val teamAName = "TeamAName.MESSAGE"
const val teamBName = "TeamBName.MESSAGE"
const val REQUEST = 1 // The request code



class MainActivity : AppCompatActivity() {
    val fragmentManager= supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val orientation = resources.configuration.orientation
        if (orientation == 1) {
            setContentView(R.layout.activity_main)
        } else {
            setContentView(R.layout.activity_landscape)
        }

        var target = intent.getStringExtra("TARGET_FRAGMENT")
        if(findViewById<FrameLayout>(R.id.framelayout) != null){
            val fragmentTransaction = fragmentManager.beginTransaction()
            if (target.equals("SCORE")){
                fragmentTransaction.add(R.id.framelayout, ScoreFragment())
                fragmentTransaction.commit()
            }
            if (target.equals("HISTORY")){
                fragmentTransaction.add(R.id.framelayout, ScoreFragment())
                fragmentTransaction.commit()
            }
        }

    }
}
