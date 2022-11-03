package com.example.easyyoga.utils

import com.example.easyyoga.R

class ConstantData {
	companion object {
		val levelList: ArrayList<LevelData> = arrayListOf()
		fun addList() {
			levelList.add(LevelData("Beginner plan", R.drawable.beginner_image))
			levelList.add(LevelData("Intermediate plan", R.drawable.intermediate_image))
			levelList.add(LevelData("Advanced plan", R.drawable.advanced_image))
		}
	}
}