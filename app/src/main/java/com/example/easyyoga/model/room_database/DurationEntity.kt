package com.example.easyyoga.model.room_database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "duration_table")
data class DurationEntity(
	@PrimaryKey(autoGenerate = true)
	val id :Int = 1,
	val date: String,
	val levelName : String,
	var totalDuration: Long,
)
