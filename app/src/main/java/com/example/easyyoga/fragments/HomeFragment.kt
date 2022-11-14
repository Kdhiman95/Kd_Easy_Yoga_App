package com.example.easyyoga.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easyyoga.adapters.LevelAdapter
import com.example.easyyoga.databinding.FragmentHomeBinding
import com.example.easyyoga.utils.Constant.Companion.cal
import com.example.easyyoga.utils.Constant.Companion.ft
import com.example.easyyoga.utils.EasyYogaApplication
import com.example.easyyoga.utils.ExercisesData.Companion.totalDurationPerDay
import com.example.easyyoga.utils.LevelsData.Companion.levelsList
import com.example.easyyoga.view_models.DurationViewModel
import com.example.easyyoga.view_models.DurationViewModelFactory

class HomeFragment : Fragment() {

	private lateinit var binding: FragmentHomeBinding
	private lateinit var durModel: DurationViewModel

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View {
		// Inflate the layout for this fragment
		binding = FragmentHomeBinding.inflate(inflater, container, false)

		val repo = ((activity as FragmentActivity).application as EasyYogaApplication).repository
		durModel =
			ViewModelProvider(this, DurationViewModelFactory(repo))[DurationViewModel::class.java]

		val pref = requireContext().getSharedPreferences("UserData", AppCompatActivity.MODE_PRIVATE)
		val editor = pref.edit()

		if (pref.getString("Weight", "")!!.isEmpty() && pref.getString("heightFeet", "")!!
				.isEmpty() && pref.getString("heightIn", "")!!.isEmpty()
		) {
			editor.putString("weight", "58")
			editor.putString("heightFeet", "5")
			editor.putString("heightIn", "7")
			editor.apply()
		}

		binding.homeFragmentRecV.layoutManager =
			LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

		return binding.root
	}

	override fun onResume() {
		durModel.getDuration(ft.format(cal.time))
		durModel.durList.observe(viewLifecycleOwner) {
			totalDurationPerDay = if (it.isNotEmpty()) {
				var dur = 0L
				for (i in it) {
					dur += i.totalDuration
				}
				dur
			} else {
				0L
			}
		}
		binding.homeFragmentRecV.adapter = LevelAdapter(levelsList, findNavController())
		super.onResume()
	}

}