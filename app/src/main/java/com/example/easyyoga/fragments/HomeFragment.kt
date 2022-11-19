package com.example.easyyoga.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.easyyoga.adapters.LevelAdapter
import com.example.easyyoga.databinding.FragmentHomeBinding
import com.example.easyyoga.utils.EasyYogaApplication
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

		binding.homeFragmentRecV.layoutManager =
			GridLayoutManager(requireContext(), 1, GridLayoutManager.HORIZONTAL, false)
		return binding.root
	}

	override fun onResume() {
		binding.homeFragmentRecV.adapter = LevelAdapter(levelsList, findNavController())
		super.onResume()
	}

}