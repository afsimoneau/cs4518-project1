package com.example.cs4518_project1

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Button;
import android.widget.TextView
import android.widget.Toast
import com.example.cs4518_project1.databinding.ActivityMainBinding
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var button3a = findViewById<Button>(R.id.button3a)
        var button3b = findViewById<Button>(R.id.button3b)
        var button2b = findViewById<Button>(R.id.button2b)
        var button2a = findViewById<Button>(R.id.button2a)
        var FreeThrowB = findViewById<Button>(R.id.FreeThrowB)
        var FreeThrowA = findViewById<Button>(R.id.FreeThrowA)
        var ResetButt = findViewById<Button>(R.id.ResetButt)
        val TeamBScore: TextView = findViewById(R.id.TeamBScore)
        val TeamAScore = findViewById<TextView>(R.id.TeamAScore)

        button3a.setOnClickListener{
            var num: Int = Integer.valueOf(TeamAScore.getText().toString())
            num += 3
            TeamAScore.text = num.toString()
        }
        button2a.setOnClickListener{
            var num: Int = Integer.valueOf(TeamAScore.getText().toString())
            num += 2
            TeamAScore.text = num.toString()
        }
        FreeThrowA.setOnClickListener{
            var num: Int = Integer.valueOf(TeamAScore.getText().toString())
            num += 1
            TeamAScore.text = num.toString()
        }
        button3b.setOnClickListener{
            var num: Int = Integer.valueOf(TeamBScore.getText().toString())
            num += 3
            TeamBScore.text = num.toString()
        }
        button2b.setOnClickListener{
            var num: Int = Integer.valueOf(TeamBScore.getText().toString())
            num += 2
            TeamBScore.text = num.toString()
        }
        FreeThrowB.setOnClickListener{
            var num: Int = Integer.valueOf(TeamBScore.getText().toString())
            num += 1
            TeamBScore.text = num.toString()
        }
        ResetButt.setOnClickListener{
            TeamAScore.text = "0"
            TeamBScore.text = "0"
        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}