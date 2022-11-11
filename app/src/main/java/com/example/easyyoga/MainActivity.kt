package com.example.easyyoga

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.example.easyyoga.utils.ExercisesData.Companion.addAdvancedPlanList
import com.example.easyyoga.utils.ExercisesData.Companion.addBeginnerPlanList
import com.example.easyyoga.utils.ExercisesData.Companion.addIntermediatePlanList
import com.example.easyyoga.utils.ExercisesData.Companion.advancedPlanList
import com.example.easyyoga.utils.ExercisesData.Companion.beginnerPlanList
import com.example.easyyoga.utils.ExercisesData.Companion.intermediatePlanList
import com.example.easyyoga.utils.LevelsData.Companion.addLevelsList
import com.example.easyyoga.utils.LevelsData.Companion.levelsList

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
	}
}