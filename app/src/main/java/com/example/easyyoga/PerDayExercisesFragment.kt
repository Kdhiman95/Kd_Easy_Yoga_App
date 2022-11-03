package com.example.easyyoga

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.easyyoga.databinding.FragmentPerDayExercisesBinding

class PerDayExercisesFragment : Fragment() {

	private lateinit var binding: FragmentPerDayExercisesBinding

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View {
		// Inflate the layout for this fragment
		binding = FragmentPerDayExercisesBinding.inflate(inflater, container, false)

		binding.perDayText.text = requireArguments().getString("Day")

		return binding.root
	}
}