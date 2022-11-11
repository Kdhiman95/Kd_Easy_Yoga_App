package com.example.easyyoga.model.room_database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ExerciseDao {

	@Insert
	suspend fun insertExercise(ex: ExercisesEntity)

	@Query("SELECT * FROM exercise_table WHERE date=:date")
	suspend fun getExerciseData(date: String): List<ExercisesEntity>

	@Insert
	suspend fun insertDuration(dur: DurationEntity)

	@Query("UPDATE duration_table SET totalDuration=:dur WHERE date=:date")
	suspend fun updateDuration(date: String, dur: Long)

	@Query("SELECT * FROM duration_table WHERE date=:date")
	suspend fun getDuration(date: String): List<DurationEntity>

}