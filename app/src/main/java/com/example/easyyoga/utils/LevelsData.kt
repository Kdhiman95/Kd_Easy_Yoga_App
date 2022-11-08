package com.example.easyyoga.utils

import com.example.easyyoga.R

class LevelsData {
	companion object {
		var levelName = ""
		var levelImg = 0
		val levelsList = ArrayList<Levels>()

		fun addLevelsList() {
			levelsList.add(Levels("Beginner plan", R.drawable.beginner_image))
			levelsList.add(Levels("Intermediate plan", R.drawable.intermediate_image))
			levelsList.add(Levels("Advanced plan", R.drawable.advanced_image))
		}
	}
}