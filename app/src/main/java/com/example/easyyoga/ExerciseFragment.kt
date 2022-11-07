package com.example.easyyoga

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.easyyoga.databinding.FragmentExerciseBinding

class ExerciseFragment : Fragment() {

	private lateinit var binding: FragmentExerciseBinding

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View {
		// Inflate the layout for this fragment
		binding = FragmentExerciseBinding.inflate(inflater, container, false)

		Glide.with(this).load(requireArguments().getInt("Img")).into(binding.exerciseFragmentImageView)

		binding.exerciseName.text = requireArguments().getString("ExerciseName")

		binding.exerciseDuration.text = requireArguments().getLong("Duration").toString()

		binding.exerciseDetail.text = requireArguments().getString("Detail")

		return binding.root
	}
}