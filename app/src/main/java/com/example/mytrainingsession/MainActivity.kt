package com.example.mytrainingsession

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        title = "Тренировки по фитнесу"
        setSupportActionBar(toolbar)

        val toneExercisesIV = findViewById<ImageView>(R.id.toneExercisesIV)
        val gymExercisesIV = findViewById<ImageView>(R.id.gymExercisesIV)
        val workoutExercisesIV = findViewById<ImageView>(R.id.workoutExercisesIV)

        toneExercisesIV.setImageResource(R.drawable.tone)
        gymExercisesIV.setImageResource(R.drawable.gym)
        workoutExercisesIV.setImageResource(R.drawable.workout)
        val exercise: Exercise? = null

        toneExercisesIV.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            val exercises = ExerciseDataBase.toneExercises
            intent.putExtra("exercises", exercises)
            startActivity(intent)
            finish()

        }
        gymExercisesIV.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            val exercises = ExerciseDataBase.gymExercises
            intent.putExtra("exercises", exercises )
            startActivity(intent)
            finish()
        }
        workoutExercisesIV.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            val exercises = ExerciseDataBase.workoutExercise
            intent.putExtra("exercises", exercises)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.menu_exit -> {
                Toast.makeText(
                    applicationContext,
                    "Программа завершена",
                    Toast.LENGTH_LONG
                ).show()
                finishAffinity()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}