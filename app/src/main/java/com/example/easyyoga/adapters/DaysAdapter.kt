package com.example.easyyoga.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.easyyoga.R
import com.google.android.material.button.MaterialButton

class DaysAdapter(
	private val list: ArrayList<Int>,
	private val totalDay: Int,
	private val navController: NavController,
) :
	RecyclerView.Adapter<DaysAdapter.DayViewHolder>() {

	inner class DayViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

		private val days: TextView = view.findViewById(R.id.dayItemText)
		private val startBtn: MaterialButton = view.findViewById(R.id.exerciseItemStartBtn)
		private val restImg: ImageView = view.findViewById(R.id.restImageView)

		fun bind(day: Int) {
			days.text = day.toString()

			if (day == totalDay+1) {
				startBtn.visibility = View.VISIBLE
			} else {
				startBtn.visibility = View.GONE
			}

			if (day % 5 == 0) {
				restImg.visibility = View.VISIBLE
				startBtn.visibility = View.GONE
			} else {
				restImg.visibility = View.GONE
			}

			val bundle = Bundle()

			if(day <= totalDay+1){
				view.setOnClickListener {
					bundle.putString("Day", "Day ${list[adapterPosition]}")
					navController.navigate(R.id.action_daysFragment_to_perDayExercisesFragment, bundle)
				}

				startBtn.setOnClickListener {
					bundle.putString("Day", "Day ${list[adapterPosition]}")
					navController.navigate(R.id.action_daysFragment_to_perDayExercisesFragment, bundle)
				}
			}
		}

	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
		val view =
			LayoutInflater.from(parent.context).inflate(R.layout.exercise_item, parent, false)
		return DayViewHolder(view)
	}

	override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
		holder.bind(list[position])
	}

	override fun getItemCount(): Int = list.size

}