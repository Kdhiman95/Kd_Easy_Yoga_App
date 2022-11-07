package com.example.easyyoga

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easyyoga.adapter.DaysAdapter
import com.example.easyyoga.databinding.FragmentDaysBinding

class DaysFragment : Fragment() {

	private lateinit var binding: FragmentDaysBinding

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View {
		// Inflate the layout for this fragment
		binding = FragmentDaysBinding.inflate(inflater, container, false)

		val plan = requireArguments().getString("Plan")
		val img = requireArguments().getInt("Img")

		binding.daysFragmentImage.setImageResource(img)
		binding.daysFragmentToolbar.title = plan
		val bundle = Bundle()
		bundle.putString("Plan",plan)
		bundle.putInt("Img", img)

		binding.daysFragmentRecV.layoutManager =
			LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

		binding.daysFragmentRecV.adapter = DaysAdapter(getDaysList(), findNavController(),bundle)

		return binding.root
	}

	private fun getDaysList(): ArrayList<String> {
		val list = arrayListOf<String>()
		for (day in 1..30) {
			list.add("Day $day")
		}
		return list
	}
}