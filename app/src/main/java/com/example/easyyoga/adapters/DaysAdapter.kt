package com.example.easyyoga.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.easyyoga.R
import com.example.easyyoga.model.room_database.DurationEntity
import com.example.easyyoga.utils.Constant.Companion.cal
import com.example.easyyoga.utils.Constant.Companion.ft
import com.example.easyyoga.utils.LevelsData.Companion.levelName
import com.example.easyyoga.view_models.DurationViewModel
import com.google.android.material.button.MaterialButton

class DaysAdapter(
	private val list: ArrayList<Int>,
	private val totalDay: Int,
	private val durModel: DurationViewModel,
	private val navController: NavController,
	private val context: Context,
) :
	RecyclerView.Adapter<DaysAdapter.DayViewHolder>() {

	inner class DayViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

		private val days: TextView = view.findViewById(R.id.dayItemText)
		private val startBtn: MaterialButton = view.findViewById(R.id.exerciseItemStartBtn)
		private val restImg: ImageView = view.findViewById(R.id.restImageView)

		private val pref = context.getSharedPreferences("UserData", AppCompatActivity.MODE_PRIVATE)

		init {
			val anim =
				AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_slide_in_bottom)
			anim.duration = 1000
			view.startAnimation(anim)
		}

		fun bind(day: Int) {
			days.text = day.toString()

			if (day == totalDay + 1) {
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

			if (day <= totalDay + 1) {
				if (day % 5 != 0) {
					view.setOnClickListener {
						bundle.putString("Day", "Day ${list[adapterPosition]}")
						navController.navigate(R.id.action_daysFragment_to_perDayExercisesFragment,
							bundle)
					}

					startBtn.setOnClickListener {
						bundle.putString("Day", "Day ${list[adapterPosition]}")
						navController.navigate(R.id.action_daysFragment_to_perDayExercisesFragment,
							bundle)
					}
				} else {
					view.setOnClickListener {
						val preRestDay = pref.getInt("preRestDay", 0)
						if (preRestDay != day && day > preRestDay) {
							val editor = pref.edit()
							editor.putInt("preRestDay", day)
							editor.apply()
							durModel.insertDuration(DurationEntity(0,
								ft.format(cal.time),
								levelName,
								0L))
						}
						bundle.putString("Day", "Day ${list[adapterPosition]}")
						navController.navigate(R.id.action_daysFragment_to_perDayExercisesFragment,
							bundle)
					}
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