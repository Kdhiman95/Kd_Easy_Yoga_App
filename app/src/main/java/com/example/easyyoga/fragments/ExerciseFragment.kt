package com.example.easyyoga.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.easyyoga.databinding.FragmentExerciseBinding
import com.example.easyyoga.utils.ExercisesData.Companion.advancedPlanList
import com.example.easyyoga.utils.ExercisesData.Companion.beginnerPlanList
import com.example.easyyoga.utils.ExercisesData.Companion.intermediatePlanList
import com.example.easyyoga.utils.LevelsData.Companion.levelName

class ExerciseFragment : Fragment() {

	private lateinit var binding: FragmentExerciseBinding

	private var exerciseId = 0
	private var index = 0

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View {
		// Inflate the layout for this fragment
		binding = FragmentExerciseBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		exerciseId = requireArguments().getInt("Id")
		val img = requireArguments().getInt("Img")
		val exerciseName = requireArguments().getString("ExerciseName")
		val detail = requireArguments().getString("Detail")
		var duration = requireArguments().getLong("Duration")

		Glide.with(this).load(img).into(binding.exerciseFragmentImageView)

		binding.exerciseName.text = exerciseName

		binding.exerciseDuration.text = duration.toString()

		binding.exerciseDetail.text = detail

		//finding exercise index for updating exercise duration
		index = when (exerciseId) {
			20, 30 -> 10
			in 11..30 -> exerciseId % 10
			else -> exerciseId
		}

		binding.plusDurationBtn.setOnClickListener {
			if (duration < 120) {
				duration += 5L
			}
			binding.exerciseDuration.text = duration.toString()
			setSaveVisibility(duration)
		}

		binding.minusDurationBtn.setOnClickListener {
			if (duration > 0) {
				duration -= 5L
			}
			binding.exerciseDuration.text = duration.toString()
			setSaveVisibility(duration)
		}

		binding.saveBtn.setOnClickListener {
			val dur = updateDurationExercise()
			setSaveVisibility(dur)
		}
	}

	private fun updateDurationExercise(): Long {
		val dur = (binding.exerciseDuration.text as String).toLong()
		when (levelName) {
			"Beginner plan" -> {
				beginnerPlanList[index - 1].duration = dur
			}
			"Intermediate plan" -> {
				intermediatePlanList[index - 1].duration = dur
			}
			"Advanced plan" -> {
				advancedPlanList[index - 1].duration = dur
			}
		}
		return dur
	}

	private fun setSaveVisibility(duration: Long) {
		val list = when (levelName) {
			"Beginner plan" -> beginnerPlanList
			"Intermediate plan" -> intermediatePlanList
			"Advanced plan" -> advancedPlanList
			else -> ArrayList()
		}
		if (duration == list[index - 1].duration) {
			binding.saveBtn.visibility = View.GONE
		} else {
			binding.saveBtn.visibility = View.VISIBLE
		}
	}
}