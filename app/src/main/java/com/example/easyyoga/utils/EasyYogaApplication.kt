package com.example.easyyoga.utils

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.easyyoga.model.repo.EasyYogaRepository
import com.example.easyyoga.model.room_database.EasyYogaDatabase

class EasyYogaApplication : Application() {

	lateinit var repository: EasyYogaRepository

	override fun onCreate() {
		super.onCreate()
		AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
		initialize()

		if (LevelsData.levelsList.isEmpty() && ExercisesData.beginnerPlanList.isEmpty() && ExercisesData.intermediatePlanList.isEmpty() && ExercisesData.advancedPlanList.isEmpty()) {
			LevelsData.addLevelsList()
			ExercisesData.addBeginnerPlanList()
			ExercisesData.addIntermediatePlanList()
			ExercisesData.addAdvancedPlanList()
		}
	}

	private fun initialize() {
		val noteDatabase = EasyYogaDatabase.getDatabase(applicationContext)
		repository = EasyYogaRepository(noteDatabase)
	}
}