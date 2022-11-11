package com.example.easyyoga.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easyyoga.model.repo.EasyYogaRepository
import com.example.easyyoga.model.room_database.ExercisesEntity
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class ExerciseViewModel(private val repository: EasyYogaRepository) : ViewModel() {

	private val exerciseMutableList = MutableLiveData<List<ExercisesEntity>>()

	val exDataList: LiveData<List<ExercisesEntity>>
		get() = exerciseMutableList

	fun insertExercise(ex: ExercisesEntity) {
		viewModelScope.launch(IO) {
			repository.insertExercise(ex)
		}
	}

	fun getExerciseData(date: String) {
		viewModelScope.launch(IO) {
			exerciseMutableList.postValue(repository.getExerciseData(date))
		}
	}
}