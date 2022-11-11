package com.example.easyyoga.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.easyyoga.model.repo.EasyYogaRepository

class DurationViewModelFactory(private val repository: EasyYogaRepository) : ViewModelProvider.Factory {
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		return DurationViewModel(repository) as T
	}
}