package com.example.easyyoga

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easyyoga.adapter.DaysAdapter
import com.example.easyyoga.adapter.PerDayExercisesAdapter
import com.example.easyyoga.databinding.FragmentDaysBinding
import com.example.easyyoga.utils.LevelsData.Companion.levelImg
import com.example.easyyoga.utils.LevelsData.Companion.levelName

class DaysFragment : Fragment() {

	private lateinit var binding: FragmentDaysBinding

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View {
		// Inflate the layout for this fragment
		binding = FragmentDaysBinding.inflate(inflater, container, false)

		binding.daysFragmentImage.setImageResource(levelImg)
		binding.daysFragmentToolbar.title = levelName

		binding.daysFragmentRecV.layoutManager =
			LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

		return binding.root
	}

	override fun onResume() {
		binding.daysFragmentRecV.adapter = DaysAdapter(getDaysList(), findNavController())
		super.onResume()
	}

	private fun getDaysList(): ArrayList<String> {
		val list = arrayListOf<String>()
		for (day in 1..30) {
			list.add("Day $day")
		}
		return list
	}
}