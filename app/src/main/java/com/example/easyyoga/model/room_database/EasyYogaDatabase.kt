package com.example.easyyoga.model.room_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ExercisesEntity::class, DurationEntity::class], version = 1)
abstract class EasyYogaDatabase : RoomDatabase() {

	abstract fun exerciseDao(): ExerciseDao

	companion object {
		private var INSTANCE: EasyYogaDatabase? = null

		fun getDatabase(context: Context): EasyYogaDatabase {
			if (INSTANCE == null) {
				synchronized(this) {
					INSTANCE = Room.databaseBuilder(
						context,
						EasyYogaDatabase::class.java,
						"easy_yoga_db",
					).build()
				}
			}
			return INSTANCE!!
		}
	}
}