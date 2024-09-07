package com.example.mytrainingsession

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class SecondActivity : AppCompatActivity() {

    private var exerciseList: ArrayList<Exercise>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        init()
    }

    private fun init(){
        val toolbarSecond = findViewById<Toolbar>(R.id.toolbarSecond)
        title = "Тренировки по фитнесу"
        setSupportActionBar(toolbarSecond)
        val listViewLV = findViewById<ListView>(R.id.listViewLV)
        exerciseList = intent.getParcelableArrayListExtra("exercises")!!
        val listAdapter = ListAdapter(this, exerciseList!!)
        listViewLV.adapter = listAdapter

        listViewLV.onItemClickListener =
            AdapterView.OnItemClickListener{ _, _, position, _ ->
                val exercise = listAdapter.getItem(position)
                val intent = Intent(this, ExerciseActivity::class.java)
                intent.putExtra("exerciseInList", exercise)
                intent.putExtra("exerciseList", this.exerciseList as ArrayList<Exercise>)
                startActivity(intent)
            }
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
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finishAffinity()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}