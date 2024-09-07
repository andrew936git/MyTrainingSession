package com.example.mytrainingsession

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import java.util.ArrayList

class ListAdapter(context: Context, exercise: ArrayList<Exercise>):
    ArrayAdapter<Exercise>(context,R.layout.list_item, exercise) {


    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val exercise: Exercise? = getItem(position)
        if (view == null) {
            view = LayoutInflater
                .from(context)
                .inflate(R.layout.list_item, parent, false)
        }
        val imageViewItemIV = view?.findViewById<ImageView>(R.id.imageViewItemIV)
        val nameTextVieTV = view?.findViewById<TextView>(R.id.nameTextVieTV)
        val timeTextVieTV = view?.findViewById<TextView>(R.id.timeTextVieTV)

        if (exercise != null) {
            imageViewItemIV?.setImageResource(exercise.gifImage)
        }
        nameTextVieTV?.text = exercise?.name
        timeTextVieTV?.text = "Время выполнения: ${exercise?.durationInSeconds}сек."

        return view!!

    }
}