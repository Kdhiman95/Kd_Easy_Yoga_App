package com.example.easyyoga.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.easyyoga.R

class DaysAdapter(
	private val list: ArrayList<String>,
	private val navController: NavController,
	private val bundle: Bundle
) :
	RecyclerView.Adapter<DaysAdapter.DayViewHolder>() {

	inner class DayViewHolder(view: View) : RecyclerView.ViewHolder(view) {

		val day: TextView

		init {
			day = view.findViewById(R.id.dayItemText)

			val plan = bundle.getString("Plan")
			val img = bundle.getInt("Img")

			view.setOnClickListener {
				val bundle = Bundle()
				bundle.putString("Day", list[adapterPosition])
				bundle.putString("Plan",plan)
				bundle.putInt("Img",img)
				navController.navigate(R.id.action_daysFragment_to_perDayExercisesFragment, bundle)
			}

		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
		val view =
			LayoutInflater.from(parent.context).inflate(R.layout.excerice_item, parent, false)
		return DayViewHolder(view)
	}

	override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
		holder.day.text = list[position]
	}

	override fun getItemCount(): Int = list.size

}