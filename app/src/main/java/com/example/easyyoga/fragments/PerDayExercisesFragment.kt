package com.example.easyyoga.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easyyoga.R
import com.example.easyyoga.adapters.PerDayExercisesAdapter
import com.example.easyyoga.databinding.FragmentPerDayExercisesBinding
import com.example.easyyoga.utils.Exercises
import com.example.easyyoga.utils.ExercisesData.Companion.advancedPlanList
import com.example.easyyoga.utils.ExercisesData.Companion.beginnerPlanList
import com.example.easyyoga.utils.ExercisesData.Companion.dailyList
import com.example.easyyoga.utils.ExercisesData.Companion.intermediatePlanList
import com.example.easyyoga.utils.LevelsData.Companion.levelImg
import com.example.easyyoga.utils.LevelsData.Companion.levelName

class PerDayExercisesFragment : Fragment() {

	private lateinit var binding: FragmentPerDayExercisesBinding

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View {
		// Inflate the layout for this fragment
		binding = FragmentPerDayExercisesBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.perDayImageView.setImageResource(levelImg)
		binding.perDayText.text = requireArguments().getString("Day")

		binding.perDayRecV.layoutManager =
			LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

		binding.exerciseStartButton.setOnClickListener {
			findNavController().navigate(R.id.action_perDayExercisesFragment_to_timerFragment)
		}

		val tempList = getList(levelName)

		val day = requireArguments().getString("Day")!!

		val perDay = (day.subSequence(day.length - 1, day.length) as String).toInt()

		val list = perDayList(tempList, perDay)

		val duration = getDuration(list)

		val min = duration / 60
		val sec = duration % 60

		val totalD = "$min min $sec s"

		binding.perDayDuration.text = totalD

		if (list.isEmpty()) {
			binding.restTextView.visibility = View.VISIBLE
			binding.restImageView.visibility = View.VISIBLE
			binding.perDayRecV.visibility = View.GONE
			binding.exerciseStartButton.visibility = View.GONE
		} else {
			binding.restTextView.visibility = View.GONE
			binding.restImageView.visibility = View.GONE
			binding.perDayRecV.visibility = View.VISIBLE
			binding.exerciseStartButton.visibility = View.VISIBLE
			dailyList = list
			binding.perDayRecV.adapter =
				PerDayExercisesAdapter(list, this, findNavController(), requireContext())
		}

	}

	private fun getList(plan: String): ArrayList<Exercises> = when (plan) {
		"Beginner plan" -> beginnerPlanList
		"Intermediate plan" -> intermediatePlanList
		"Advanced plan" -> advancedPlanList
		else -> ArrayList()
	}

	private fun perDayList(tempList: ArrayList<Exercises>, day: Int): ArrayList<Exercises> {
		val list = ArrayList<Exercises>()
		if (day % 5 == 0) {
			return list
		} else if (day % 2 == 0) {
			list.addAll(tempList.subList(0, 5))
		} else {
			list.addAll(tempList.subList(5, 10))
		}

		return list
	}

	private fun getDuration(list: ArrayList<Exercises>): Long {
		var duration = 0L

		for (i in list) {
			duration += i.duration
			duration += 10L
		}

		return duration
	}
}