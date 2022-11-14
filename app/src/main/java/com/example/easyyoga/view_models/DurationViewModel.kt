package com.example.easyyoga.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easyyoga.model.repo.EasyYogaRepository
import com.example.easyyoga.model.room_database.DurationEntity
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class DurationViewModel(private val repository: EasyYogaRepository) : ViewModel() {

	private val cal = Calendar.getInstance()
	private val ft = SimpleDateFormat("yyyy-MM-dd", Locale.US)

	private val durMutableList = MutableLiveData<List<DurationEntity>>()
	val durList: LiveData<List<DurationEntity>>
		get() = durMutableList

	private val levelDurMutableList = MutableLiveData<List<DurationEntity>>()
	val levelDurList: LiveData<List<DurationEntity>>
		get() = levelDurMutableList

	private val mutableNoOfDays = MutableLiveData<List<DurationEntity>>()
	val noOfDay: LiveData<List<DurationEntity>>
		get() = mutableNoOfDays

	init {
		getDuration(ft.format(cal.time))
	}

	fun insertDuration(dur: DurationEntity) {
		viewModelScope.launch(IO) {
			repository.insertDuration(dur)
		}
	}

	fun updateDuration(date: String, dur: Long, level: String) {
		viewModelScope.launch(IO) {
			repository.updateDuration(date, dur, level)
		}
	}

	fun getDuration(date: String) {
		viewModelScope.launch(IO) {
			durMutableList.postValue(repository.getDuration(date))
		}
	}

	fun getLevelDuration(date: String, level: String) {
		viewModelScope.launch(IO) {
			levelDurMutableList.postValue(repository.getLevelDuration(date, level))
		}
	}

	fun getNoOfDays(level: String) {
		viewModelScope.launch {
			mutableNoOfDays.postValue(repository.getNoOfDays(level))
		}
	}
}