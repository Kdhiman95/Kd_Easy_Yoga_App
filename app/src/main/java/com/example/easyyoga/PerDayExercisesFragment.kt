package com.example.easyyoga

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easyyoga.adapter.PerDayExercisesAdapter
import com.example.easyyoga.databinding.FragmentPerDayExercisesBinding
import com.example.easyyoga.utils.Exercises
import com.example.easyyoga.viewmodels.PerDayViewModel

class PerDayExercisesFragment : Fragment() {

	private lateinit var binding: FragmentPerDayExercisesBinding
	private lateinit var modal: PerDayViewModel

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View {
		// Inflate the layout for this fragment
		binding = FragmentPerDayExercisesBinding.inflate(inflater, container, false)


		modal = ViewModelProvider(this)[PerDayViewModel::class.java]

		val days = requireArguments().getString("Day")!!
		binding.perDayText.text = days

		binding.perDayImageView.setImageResource(requireArguments().getInt("Img"))

		binding.perDayRecV.layoutManager =
			LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

		val tempList = getList()

		val day = (days.subSequence(days.length - 1, days.length) as String).toInt()

		val list = perDayList(tempList, day)

		val duration = getDuration(list)

		binding.perDayDuration.text = (duration / 60).toString()

		if(list.isEmpty()){
			binding.restTextView.visibility = View.VISIBLE
			binding.perDayRecV.visibility = View.GONE
		}else {
			binding.restTextView.visibility = View.GONE
			binding.perDayRecV.visibility = View.VISIBLE
			binding.perDayRecV.adapter = PerDayExercisesAdapter(list, this, findNavController())
		}

		return binding.root
	}

	private fun getList(): ArrayList<Exercises> = when (requireArguments().getString("Plan")) {
		"Beginner plan" -> modal.beginnerPlanList
		"Intermediate plan" -> modal.intermediatePlanList
		"Advanced plan" -> modal.advancedPlanList
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

	private fun getDuration(tempList: ArrayList<Exercises>): Long {
		var duration = 0L

		for (i in tempList) {
			duration += i.duration
			duration += 10L
		}

		return duration
	}
}