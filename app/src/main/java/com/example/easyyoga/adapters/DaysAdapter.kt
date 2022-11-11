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
	private val list: ArrayList<String>,
	private val navController: NavController,
) :
	RecyclerView.Adapter<DaysAdapter.DayViewHolder>() {

	inner class DayViewHolder(view: View) : RecyclerView.ViewHolder(view) {

		private val days: TextView
		private val startBtn: MaterialButton
		private val restImg: ImageView

		init {
			days = view.findViewById(R.id.dayItemText)
			startBtn = view.findViewById(R.id.exerciseItemStartBtn)
			restImg = view.findViewById(R.id.restImageView)

			val bundle = Bundle()

			view.setOnClickListener {
				bundle.putString("Day", list[adapterPosition])
				navController.navigate(R.id.action_daysFragment_to_perDayExercisesFragment, bundle)
			}

			startBtn.setOnClickListener {
				bundle.putString("Day", list[adapterPosition])
				navController.navigate(R.id.action_daysFragment_to_perDayExercisesFragment, bundle)
			}
		}

		fun bind(day: String) {
			days.text = day
			val d = (day.subSequence(day.length - 1, day.length) as String).toInt()
			if (d % 5 == 0) {
				restImg.visibility = View.VISIBLE
				startBtn.visibility = View.GONE
			} else {
				restImg.visibility = View.GONE
				startBtn.visibility = View.VISIBLE
			}
		}

	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
		val view =
			LayoutInflater.from(parent.context).inflate(R.layout.excerice_item, parent, false)
		return DayViewHolder(view)
	}

	override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
		holder.bind(list[position])
	}

	override fun getItemCount(): Int = list.size

}