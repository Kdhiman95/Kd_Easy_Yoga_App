package com.example.easyyoga.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.easyyoga.model.repo.EasyYogaRepository

class ExerciseViewModelFactory(private val repo: EasyYogaRepository): ViewModelProvider.Factory {
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		return ExerciseViewModel(repo) as T
	}
}