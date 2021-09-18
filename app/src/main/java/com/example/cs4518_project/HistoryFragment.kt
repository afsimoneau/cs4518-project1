package com.example.cs4518_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


class HistoryFragment : Fragment() {

    private lateinit var history: History

    fun OnCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        history = History()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        return view
    }
}