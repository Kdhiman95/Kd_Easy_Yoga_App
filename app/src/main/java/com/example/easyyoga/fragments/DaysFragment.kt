package com.example.easyyoga.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easyyoga.adapters.DaysAdapter
import com.example.easyyoga.databinding.FragmentDaysBinding
import com.example.easyyoga.utils.Constant.Companion.cal
import com.example.easyyoga.utils.Constant.Companion.entityPresentOrNot
import com.example.easyyoga.utils.Constant.Companion.ft
import com.example.easyyoga.utils.EasyYogaApplication
import com.example.easyyoga.utils.ExercisesData.Companion.levelDurationPerDay
import com.example.easyyoga.utils.LevelsData.Companion.levelImg
import com.example.easyyoga.utils.LevelsData.Companion.levelName
import com.example.easyyoga.view_models.DurationViewModel
import com.example.easyyoga.view_models.DurationViewModelFactory

class DaysFragment : Fragment() {

	private lateinit var binding: FragmentDaysBinding
	private lateinit var durModel: DurationViewModel

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View {
		// Inflate the layout for this fragment
		binding = FragmentDaysBinding.inflate(inflater, container, false)

		binding.daysFragmentImage.setImageResource(levelImg)
		binding.daysFragmentToolbar.title = levelName

		val repo = ((activity as FragmentActivity).application as EasyYogaApplication).repository

		durModel =
			ViewModelProvider(this, DurationViewModelFactory(repo))[DurationViewModel::class.java]

		binding.daysFragmentRecV.layoutManager =
			LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

		durModel.getLevelDuration(ft.format(cal.time), levelName)
		durModel.levelDurList.observe(viewLifecycleOwner) {
			levelDurationPerDay = if (it.isNotEmpty()) {
				entityPresentOrNot = it
				it[0].totalDuration
			} else {
				0L
			}
		}

		durModel.getNoOfDays(levelName)

		return binding.root
	}

	override fun onResume() {
		durModel.noOfDay.observe(viewLifecycleOwner) {
			binding.daysFragmentRecV.adapter =
				DaysAdapter(getDaysList(), it.size,durModel, findNavController(),requireContext())
		}
		super.onResume()
	}

	private fun getDaysList(): ArrayList<Int> {
		val list = arrayListOf<Int>()
		for (day in 1..30) {
			list.add(day)
		}
		return list
	}
}