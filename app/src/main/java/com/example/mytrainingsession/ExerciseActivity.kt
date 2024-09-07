package com.example.mytrainingsession

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class ExerciseActivity : AppCompatActivity() {

    private var exerciseList: ArrayList<Exercise>? = null
    private var exercise: Exercise? = null
    private lateinit var timer: CountDownTimer
    private lateinit var startStopTextViewTV: TextView
    private lateinit var timerTextViewTV: TextView
    private lateinit var exerciseImageViewIV: ImageView
    private lateinit var startButtonBT: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_exercise)
        init()
    }

    private fun init(){
        val toolbarExercise = findViewById<Toolbar>(R.id.toolbarExercise)
        title = "Начало тренировки"
        setSupportActionBar(toolbarExercise)
        exercise = intent.getParcelableExtra("exerciseInList")
        exerciseList = intent.getParcelableArrayListExtra("exerciseList")!!


        val nameTextViewExerciseTV = findViewById<TextView>(R.id.nameTextViewExerciseTV)
        val descriptionTextViewTV = findViewById<TextView>(R.id.descriptionTextViewTV)
        startStopTextViewTV = findViewById(R.id.startStopTextViewTV)
        timerTextViewTV = findViewById(R.id.timerTextViewTV)
        exerciseImageViewIV = findViewById(R.id.exerciseImageViewIV)
        startButtonBT = findViewById(R.id.startButtonBT)

        nameTextViewExerciseTV.text = exercise?.name
        descriptionTextViewTV.text = exercise?.description

        startButtonBT.setOnClickListener{
            startWorkout()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun startWorkout() {
        startStopTextViewTV.text = "Начало тренировки"
        startButtonBT.isEnabled = false
        startButtonBT.text = "Процесс тренировки"
        exerciseImageViewIV.setImageResource(exercise!!.gifImage)
        timerTextViewTV.text = formatTime(exercise?.durationInSeconds)
        timer = object: CountDownTimer(exercise!!.durationInSeconds * 1000L, 1000){
            override fun onTick(millisUntilFinished: Long) {
                timerTextViewTV.text = formatTime((millisUntilFinished / 1000).toInt())
            }

            override fun onFinish() {
                startStopTextViewTV.text = "Упражнение завершено"
                exerciseImageViewIV.visibility = View.VISIBLE
                startButtonBT.isEnabled = true
                startButtonBT.text = "Начать снова"
            }
        }.start()
    }

    @SuppressLint("DefaultLocale")
    private fun formatTime(second: Int?): String {
        val minutes = second!! / 60
        val remainingSeconds = second % 60
        return String.format("%02d:%02d", minutes, remainingSeconds)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_second, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.exit_menu -> {
                Toast.makeText(
                    applicationContext,
                    "Программа завершена",
                    Toast.LENGTH_LONG
                ).show()
                finishAffinity()
                finish()
            }
            R.id.back_menu -> {
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("exercises", exerciseList)
                startActivity(intent)
                finishAffinity()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}