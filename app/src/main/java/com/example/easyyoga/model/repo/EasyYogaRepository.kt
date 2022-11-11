package com.example.easyyoga.model.repo

import com.example.easyyoga.model.room_database.DurationEntity
import com.example.easyyoga.model.room_database.EasyYogaDatabase
import com.example.easyyoga.model.room_database.ExercisesEntity

class EasyYogaRepository(private val easyYogaDatabase: EasyYogaDatabase) {

	suspend fun insertExercise(ex: ExercisesEntity) {
		easyYogaDatabase.exerciseDao().insertExercise(ex)
	}

	suspend fun getExerciseData(date: String): List<ExercisesEntity> =
		easyYogaDatabase.exerciseDao().getExerciseData(date)

	suspend fun insertDuration(dur: DurationEntity) {
		easyYogaDatabase.exerciseDao().insertDuration(dur)
	}

	suspend fun updateDuration(date: String, dur: Long) {
		easyYogaDatabase.exerciseDao().updateDuration(date, dur)
	}

	suspend fun getDuration(date: String): List<DurationEntity> =
		easyYogaDatabase.exerciseDao().getDuration(date)
}