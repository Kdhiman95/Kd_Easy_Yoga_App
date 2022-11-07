package com.example.easyyoga

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easyyoga.adapter.LevelAdapter
import com.example.easyyoga.databinding.FragmentHomeBinding
import com.example.easyyoga.viewmodels.HomeViewModel

class HomeFragment : Fragment() {

	private lateinit var binding: FragmentHomeBinding

//	val t = TextToSpeech(requireContext(), {}, "Hello")

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View {
		// Inflate the layout for this fragment
		binding = FragmentHomeBinding.inflate(inflater, container, false)

		val homeModal = ViewModelProvider(this)[HomeViewModel::class.java]

		binding.homeFragmentRecV.layoutManager =
			LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

		binding.homeFragmentRecV.adapter = LevelAdapter(homeModal.getList(), findNavController())

		return binding.root
	}
}