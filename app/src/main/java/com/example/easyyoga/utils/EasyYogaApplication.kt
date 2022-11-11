package com.example.easyyoga.utils

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.easyyoga.model.repo.EasyYogaRepository
import com.example.easyyoga.model.room_database.EasyYogaDatabase
import com.example.easyyoga.utils.ExercisesData.Companion.totalDurationPerDay

class EasyYogaApplication : Application() {

	lateinit var repository: EasyYogaRepository

	override fun onCreate() {
		super.onCreate()
		AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
		initialize()

		val pref = this.getSharedPreferences("UserData", AppCompatActivity.MODE_PRIVATE)

		if (LevelsData.levelsList.isEmpty() && ExercisesData.beginnerPlanList.isEmpty() && ExercisesData.intermediatePlanList.isEmpty() && ExercisesData.advancedPlanList.isEmpty()) {
			LevelsData.addLevelsList()
			ExercisesData.addBeginnerPlanList()
			ExercisesData.addIntermediatePlanList()
			ExercisesData.addAdvancedPlanList()
		}
		totalDurationPerDay = pref.getLong("totalDurationPerDay",0L)
	}

	private fun initialize() {
		val noteDatabase = EasyYogaDatabase.getDatabase(applicationContext)
		repository = EasyYogaRepository(noteDatabase)
	}
}