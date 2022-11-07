package com.example.easyyoga.viewmodels

import androidx.lifecycle.ViewModel
import com.example.easyyoga.R
import com.example.easyyoga.utils.LevelData

class HomeViewModel : ViewModel() {

	private val planList = ArrayList<LevelData>()

	init {
		addPlanList()
	}

	 private fun addPlanList() {
		planList.add(LevelData("Beginner plan", R.drawable.beginner_image))
		planList.add(LevelData("Intermediate plan", R.drawable.intermediate_image))
		planList.add(LevelData("Advanced plan", R.drawable.advanced_image))
	}

	fun getList() : ArrayList<LevelData> {
		return planList
	}
}