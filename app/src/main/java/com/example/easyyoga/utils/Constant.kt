package com.example.easyyoga.utils

import androidx.navigation.NavController
import com.example.easyyoga.model.room_database.DurationEntity
import java.text.SimpleDateFormat
import java.util.*

class Constant {
	companion object {
		val cal: Calendar = Calendar.getInstance()
		val ft = SimpleDateFormat("yyyy-MM-dd", Locale.US)
		var entityPresentOrNot: List<DurationEntity> = listOf()
	}
}