package com.example.cs4518_project

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.*


class ScoreFragment : Fragment() {
    private val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    private lateinit var historyButt: Button
    private lateinit var viewModel: TeamViewModel
    private lateinit var button3a: Button
    private lateinit var button3b: Button
    private lateinit var button2a: Button
    private lateinit var button2b: Button
    private lateinit var freeThrowA: Button
    private lateinit var freeThrowB: Button
    private lateinit var resetButt: Button
    private lateinit var teamBScore: TextView
    private lateinit var teamAScore: TextView
    private lateinit var teamAText: TextView
    private lateinit var teamBText: TextView
    private lateinit var saveButt: Button
    private lateinit var teamAChangePhoto: Button
    private lateinit var teamBChangePhoto: Button
    private lateinit var weather: TextView
    private lateinit var myHistory: History
    private lateinit var teamAPhotoFile: File
    private lateinit var teamBPhotoFile: File


    private var historyRepository: HistoryRepository = HistoryRepository.get()

    val PHOTO_A_REQUEST = 97
    val PHOTO_B_REQUEST = 98

    interface Callbacks {
        fun onClickHistoryFromScore(teamAScore: Int, teamBScore: Int)
        fun onClickSaveFromScore(historyId: UUID)
    }

    private var callbacks: Callbacks? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        val view = inflater.inflate(R.layout.fragment_score, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(TeamViewModel::class.java)
        val controller = TeamController(viewModel)
        myHistory = History()
        findViews(view)
        getMyData()
        setListeners(controller)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(TeamViewModel::class.java)
        viewModel.teamAScore.observe(viewLifecycleOwner, {
            teamAScore.text = it.toString()
        })
        viewModel.teamBScore.observe(viewLifecycleOwner, {
            teamBScore.text = it.toString()
        })
        viewModel.teamAName.observe(viewLifecycleOwner, {
            teamAText.text = it
        })
        viewModel.teamBName.observe(viewLifecycleOwner, {
            teamBText.text = it
        })

    }

    private fun findViews(view: View) {
        button3a = view.findViewById(R.id.button3a)
        button3b = view.findViewById(R.id.button3b)
        button2a = view.findViewById(R.id.button2a)
        button2b = view.findViewById(R.id.button2b)
        freeThrowB = view.findViewById(R.id.freeThrowB)
        freeThrowA = view.findViewById(R.id.freeThrowA)
        resetButt = view.findViewById(R.id.resetButt_score)
        historyButt = view.findViewById(R.id.historyButt_score)
        saveButt = view.findViewById(R.id.saveButt_score)
        teamAChangePhoto = view.findViewById<Button>(R.id.teamAChangePhoto).apply {
            val packageManager: PackageManager = requireActivity().packageManager
            val captureImage = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            teamAPhotoFile = historyRepository.getTeamAPhotoFile(myHistory)
            val photoUri = FileProvider.getUriForFile(requireActivity(),"com.example.cs4518_project.fileprovider",teamAPhotoFile)

            setOnClickListener {
                Log.d("photo button", "team A")
                captureImage.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)

                val cameraActivities: List<ResolveInfo> =
                    packageManager.queryIntentActivities(
                        captureImage,
                        PackageManager.MATCH_DEFAULT_ONLY
                    )

                for (cameraActivity in cameraActivities) {
                    requireActivity().grantUriPermission(
                        cameraActivity.activityInfo.packageName,
                        photoUri,
                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    )
                }
                startActivityForResult(captureImage, PHOTO_A_REQUEST)

            }
        }
        teamBChangePhoto = view.findViewById<Button>(R.id.teamBChangePhoto).apply {
            val packageManager: PackageManager = requireActivity().packageManager
            val captureImage = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            teamBPhotoFile = historyRepository.getTeamBPhotoFile(myHistory)
            val photoUri = FileProvider.getUriForFile(requireActivity(),"com.example.cs4518_project.fileprovider",teamBPhotoFile)

            setOnClickListener {
                Log.d("photo button", "team B")
                captureImage.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)

                val cameraActivities: List<ResolveInfo> =
                    packageManager.queryIntentActivities(
                        captureImage,
                        PackageManager.MATCH_DEFAULT_ONLY
                    )

                for (cameraActivity in cameraActivities) {
                    requireActivity().grantUriPermission(
                        cameraActivity.activityInfo.packageName,
                        photoUri,
                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    )
                }
                startActivityForResult(captureImage, PHOTO_B_REQUEST)
            }
        }

        weather = view.findViewById(R.id.weather)
        teamAScore = view.findViewById<TextView>(R.id.teamAScore).apply {
            text = viewModel.teamAScore.value.toString()
        }
        teamBScore = view.findViewById<TextView>(R.id.teamBScore).apply {
            text = viewModel.teamBScore.value.toString()
        }
        teamAText = view.findViewById<TextView>(R.id.teamAText).apply {
            text = viewModel.teamAName.value
        }
        teamBText = view.findViewById<TextView>(R.id.teamBText).apply {
            text = viewModel.teamBName.value
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            Log.d("Error", "Did not properly receive request")
            return
        } else {
            if (requestCode == REQUEST) {
                Log.d("Success", "Back button clicked")
            } else if (requestCode == PHOTO_A_REQUEST) {
                Log.d(this::class.java.toString(), "activity result team A")

            } else if (requestCode == PHOTO_B_REQUEST) {
                Log.d(this::class.java.toString(), "activity result team B")

            }
        }

    }

    private fun setListeners(controller: TeamController) {
        button3a.setOnClickListener {
            val score: Int = controller.getScoreA() + 3
            controller.setScoreA(score)
            teamAScore.text = viewModel.teamAScore.value.toString()
            Log.d(viewModel.teamAName.value, "+3 points")
        }
        button2a.setOnClickListener {
            val score: Int = controller.getScoreA() + 2
            controller.setScoreA(score)
            teamAScore.text = viewModel.teamAScore.value.toString()
            Log.d(viewModel.teamAName.value, "+2 points")

        }
        freeThrowA.setOnClickListener {
            val score: Int = controller.getScoreA() + 1
            controller.setScoreA(score)
            teamAScore.text = viewModel.teamAScore.value.toString()
            Log.d(viewModel.teamAName.value, "+1 point")

        }
        button3b.setOnClickListener {
            val score: Int = controller.getScoreB() + 3
            controller.setScoreB(score)
            teamBScore.text = viewModel.teamBScore.value.toString()
            Log.d(viewModel.teamBName.value, "+3 points")

        }
        button2b.setOnClickListener {
            val score: Int = controller.getScoreB() + 2
            controller.setScoreB(score)
            teamBScore.text = viewModel.teamBScore.value.toString()
            Log.d(viewModel.teamBName.value, "+2 points")

        }
        freeThrowB.setOnClickListener {
            val score: Int = controller.getScoreB() + 1
            controller.setScoreB(score)
            teamBScore.text = viewModel.teamBScore.value.toString()
            Log.d(viewModel.teamBName.value, "+1 point")

        }
        resetButt.setOnClickListener {
            Log.d(this::class.java.toString(), "ResetButt")

            controller.setScoreA(0)
            controller.setScoreB(0)
            controller.setTeamAName("Team A")
            controller.setTeamBName("Team B")
            teamAText.text = "Team A"
            teamBText.text = "Team B"
            teamAScore.text = "0"
            teamBScore.text = "0"
            viewModel.reset()
        }
        saveButt.setOnClickListener {
            Log.d(this::class.java.toString(), "SaveButt")
            myHistory.teamAName = teamAText.text as String
            myHistory.teamBName = teamBText.text as String
            myHistory.teamAScore = Integer.parseInt(teamAScore.text as String)
            myHistory.teamBScore = Integer.parseInt(teamBScore.text as String)

            historyRepository.addHistory(myHistory)

            callbacks?.onClickSaveFromScore(myHistory.id)
        }
        historyButt.setOnClickListener {
            Log.d(this::class.java.toString(), "HistoryButt")

            myHistory.teamAName = teamAText.text as String
            myHistory.teamBName = teamBText.text as String
            myHistory.teamAScore = Integer.parseInt(teamAScore.text as String)
            myHistory.teamBScore = Integer.parseInt(teamBScore.text as String)

            historyRepository.addHistory(myHistory)

            callbacks?.onClickHistoryFromScore(
                Integer.parseInt(teamAScore.text as String),
                Integer.parseInt(teamBScore.text as String)
            )
        }
    }

    companion object {
        fun newInstance(): ScoreFragment {
            return ScoreFragment()
        }
    }

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(RetrofitInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<WeatherData> {
            var newweatherReport: String = ""
            override fun onResponse(
                call: Call<WeatherData>, response: Response<WeatherData>
            ) {
                //val responseBody = response.body().toString()
                val weatherReport = response.body()?.main?.temp
                if (weatherReport != null) {
                    newweatherReport = (((weatherReport - 273.15) * 9 / 5) + 32).toInt().toString()
                }
                val city = response.body()?.name.toString()

                view?.findViewById(R.id.weather) as TextView

                weather.text =
                    "In the city of " + city + " the current weather is " + newweatherReport + "° Fahrenheit"
            }

            override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                Log.d("Failure", "Did not reach")
            }

        }
        )
    }

}


/*

 */
