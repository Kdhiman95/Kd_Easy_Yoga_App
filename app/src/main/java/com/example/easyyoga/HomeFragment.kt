package com.example.easyyoga

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easyyoga.adapter.LevelAdapter
import com.example.easyyoga.databinding.FragmentHomeBinding
import com.example.easyyoga.utils.ConstantData.Companion.addList
import com.example.easyyoga.utils.ConstantData.Companion.levelList

class HomeFragment : Fragment() {

	lateinit var binding: FragmentHomeBinding

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View {
		// Inflate the layout for this fragment
		binding = FragmentHomeBinding.inflate(inflater, container, false)

		if(levelList.isEmpty()){
			addList()
		}

		binding.homeFragmentRecV.layoutManager =
			LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

		binding.homeFragmentRecV.adapter = LevelAdapter(levelList)

		return binding.root
	}
}