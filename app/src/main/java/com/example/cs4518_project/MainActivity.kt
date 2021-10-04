package com.example.cs4518_project

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import java.util.*


const val REQUEST = 1 // The request code


class MainActivity : AppCompatActivity(), HistoryListFragment.Callbacks,DetailFragment.Callbacks,ScoreFragment.Callbacks {
    private val fragmentManager = supportFragmentManager

    override fun onSelectHistoryFromHistory(historyId: UUID) {
        Log.d(this::class.java.toString(), "History Selected $historyId")
        val fragment = DetailFragment.newInstance(historyId)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onClickNewGameFromHistory() {
        Log.d(this::class.java.toString(), "New Game from History")
        val fragment = ScoreFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onClickNewGameFromDetail() {
        Log.d(this::class.java.toString(), "New Game from Detail")
        val fragment = ScoreFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onClickHistoryFromDetail() {
        Log.d(this::class.java.toString(), "History from Detail")
        val fragment = HistoryListFragment.newInstance("")
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onClickHistoryFromScore(teamAScore:Int, teamBScore:Int) {
        Log.d(this::class.java.toString(), "History from Score")
        var winner:String
        if (teamAScore>teamBScore){
            winner="TEAM_A"
        }
        else if (teamAScore<teamBScore){
            winner="TEAM_B"
        }
        else{
            winner="DRAW"
        }
        val fragment = HistoryListFragment.newInstance(winner)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onClickSaveFromScore(historyId:UUID) {
        Log.d(this::class.java.toString(), "Save from Score")
        val fragment = DetailFragment.newInstance(historyId)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .commit()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val orientation = resources.configuration.orientation
        if (orientation == 1) {
            setContentView(R.layout.activity_main)
        } else {
            setContentView(R.layout.activity_main_land)
        }


        val target = intent.getStringExtra("TARGET_FRAGMENT")
        if (findViewById<FrameLayout>(R.id.fragment_container) != null) {
            val fragmentTransaction = fragmentManager.beginTransaction()
            when (target) {
                "SCORE" -> fragmentTransaction.replace(R.id.fragment_container, ScoreFragment())
                "HISTORY" -> fragmentTransaction.replace(R.id.fragment_container, HistoryListFragment())
                "DETAIL" -> fragmentTransaction.replace(R.id.fragment_container, DetailFragment())
                else -> fragmentTransaction.replace(R.id.fragment_container, ScoreFragment())
            }
            Log.d(this::class.java.toString(), "Intent: $target")
            fragmentTransaction.commit()
        }
    }



}
