package com.example.easyyoga.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easyyoga.adapter.LevelAdapter
import com.example.easyyoga.databinding.FragmentHomeBinding
import com.example.easyyoga.utils.LevelsData.Companion.levelsList

class HomeFragment : Fragment() {

	private lateinit var binding: FragmentHomeBinding

//	val t = TextToSpeech(requireContext(), {}, "Hello")

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View {
		// Inflate the layout for this fragment
		binding = FragmentHomeBinding.inflate(inflater, container, false)

		val pref = requireContext().getSharedPreferences("UserData", AppCompatActivity.MODE_PRIVATE)
		val editor = pref.edit()

		if (pref.getString("Weight", "")!!.isEmpty() && pref.getString("heightFeet", "")!!
				.isEmpty() && pref.getString("heightIn", "")!!.isEmpty()
		) {
			editor.putString("weight", "60")
			editor.putString("heightFeet", "5")
			editor.putString("heightIn", "7")
			editor.apply()
		}

		binding.homeFragmentRecV.layoutManager =
			LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

		return binding.root
	}

	override fun onResume() {
		binding.homeFragmentRecV.adapter = LevelAdapter(levelsList, findNavController())
		super.onResume()
	}

}