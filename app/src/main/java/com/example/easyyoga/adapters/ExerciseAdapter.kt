package com.example.easyyoga.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.easyyoga.R
import com.example.easyyoga.model.room_database.ExercisesEntity

class ExerciseAdapter(private val list: List<ExercisesEntity>, private val context: Context) :
	RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

	inner class ExerciseViewHolder(view: View) : RecyclerView.ViewHolder(view) {

		private val image: ImageView
		private val exerciseName: TextView
		private val exerciseDuration: TextView

		init {
			image = view.findViewById(R.id.exerciseImageView)
			exerciseName = view.findViewById(R.id.exerciseName)
			exerciseDuration = view.findViewById(R.id.exerciseDuration)

			val anim = AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_slide_in_bottom)
			anim.duration = 1000
			view.startAnimation(anim)
		}

		fun bind(ex : ExercisesEntity) {
			Glide.with(context).load(ex.img).into(image)
			exerciseName.text = ex.exName
			exerciseDuration.text = ex.exDuration.toString()
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
		val view =
			LayoutInflater.from(parent.context).inflate(R.layout.exercise_layout, parent, false)
		return ExerciseViewHolder(view)
	}

	override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
		holder.bind(list[position])
	}

	override fun getItemCount(): Int = list.size

}