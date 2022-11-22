package com.example.easyyoga.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.easyyoga.R
import com.example.easyyoga.fragments.PerDayExercisesFragment
import com.example.easyyoga.utils.Exercises

class PerDayExercisesAdapter(
	private val list: ArrayList<Exercises>,
	private val perDayExercisesFragment: PerDayExercisesFragment,
	private val navController: NavController,
	private val context: Context,
) :
	RecyclerView.Adapter<PerDayExercisesAdapter.PerDayViewHolder>() {
	inner class PerDayViewHolder(view: View) : RecyclerView.ViewHolder(view) {

		private val image: ImageView
		private val exerciseName: TextView
		private val exerciseDuration: TextView

		init {
			image = view.findViewById(R.id.exerciseImageView)
			exerciseName = view.findViewById(R.id.exerciseName)
			exerciseDuration = view.findViewById(R.id.exerciseDuration)

			val anim =
				AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_slide_in_bottom)
			anim.duration = 1000
			view.startAnimation(anim)

			view.setOnClickListener {
				val temp = list[adapterPosition]
				val bundle = Bundle()
				bundle.putInt("Id", temp.id)
				bundle.putInt("Img", temp.img)
				bundle.putString("ExerciseName", temp.exerciseName)
				bundle.putString("Detail", temp.detail)
				bundle.putLong("Duration", temp.duration)
				navController.navigate(R.id.action_perDayExercisesFragment_to_exerciseFragment,
					bundle)
			}
		}

		fun bind(exercises: Exercises) {
			Glide.with(perDayExercisesFragment).load(exercises.img).into(image)
			exerciseName.text = exercises.exerciseName
			exerciseDuration.text = exercises.duration.toString()
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerDayViewHolder {
		val view =
			LayoutInflater.from(parent.context).inflate(R.layout.exercise_layout, parent, false)
		return PerDayViewHolder(view)
	}

	override fun onBindViewHolder(holder: PerDayViewHolder, position: Int) {
		holder.bind(list[position])
	}

	override fun getItemCount(): Int = list.size
}