package com.example.easyyoga.model.room_database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise_table")
data class ExercisesEntity(
	@PrimaryKey(autoGenerate = true)
	val dId: Int = 1,
	val exId: Int,
	val exName: String,
	val exDetail: String,
	val exDuration: Long,
	val img: Int,
	val date: String,
)