package com.example.easyyoga.fragments

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.easyyoga.R
import com.example.easyyoga.databinding.FragmentTimerBinding
import com.example.easyyoga.model.room_database.DurationEntity
import com.example.easyyoga.model.room_database.ExercisesEntity
import com.example.easyyoga.utils.Constant.Companion.cal
import com.example.easyyoga.utils.Constant.Companion.entityPresentOrNot
import com.example.easyyoga.utils.Constant.Companion.ft
import com.example.easyyoga.utils.EasyYogaApplication
import com.example.easyyoga.utils.Exercises
import com.example.easyyoga.utils.ExercisesData.Companion.dailyList
import com.example.easyyoga.utils.ExercisesData.Companion.levelDurationPerDay
import com.example.easyyoga.utils.LevelsData.Companion.levelName
import com.example.easyyoga.view_models.DurationViewModel
import com.example.easyyoga.view_models.DurationViewModelFactory
import com.example.easyyoga.view_models.ExerciseViewModel
import com.example.easyyoga.view_models.ExerciseViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.*

class TimerFragment : Fragment() {

	private lateinit var tts: TextToSpeech
	private lateinit var binding: FragmentTimerBinding
	private lateinit var restTimer: CountDownTimer
	private lateinit var exerciseModel: ExerciseViewModel
	private lateinit var durModel: DurationViewModel

	private var exTimer: CountDownTimer? = null

	private var countEx = 0

	private var i = 0

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View {
		// Inflate the layout for this fragment
		binding = FragmentTimerBinding.inflate(inflater, container, false)

		val repo = ((activity as FragmentActivity).application as EasyYogaApplication).repository

		exerciseModel =
			ViewModelProvider(this, ExerciseViewModelFactory(repo))[ExerciseViewModel::class.java]

		durModel =
			ViewModelProvider(this, DurationViewModelFactory(repo))[DurationViewModel::class.java]

		binding.dailyProgressIndicator.max = dailyList.size

		startTimer(i, requireContext())

		requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
			object : OnBackPressedCallback(true) {
				override fun handleOnBackPressed() {
					showDialog(requireContext())
				}
			})

		binding.backBtn.setOnClickListener {
			showDialog(requireContext())
		}

		binding.timerFragmentNextBtn.setOnClickListener {
			++i
			if(i< dailyList.size){
				restTimer.cancel()
				exTimer?.cancel()
				startTimer(i,requireContext())
				binding.dailyProgressIndicator.progress = ++countEx
			}
			tts.stop()
		}
		binding.timerFragmentPreBtn.setOnClickListener {
			--i
			if(i>=0){
				restTimer.cancel()
				exTimer?.cancel()
				startTimer(i,requireContext())
				binding.dailyProgressIndicator.progress = --countEx
			}
			tts.stop()
		}

		return binding.root
	}

	private fun showDialog(context: Context){
		val alertDialog = MaterialAlertDialogBuilder(context)
		alertDialog.setMessage("Are you sure you want to Quit the exercise?")
			.setCancelable(false)
			.setPositiveButton("Yes") { _, _ ->
				setOrUpdateDuration()
				findNavController().navigate(R.id.action_timerFragment_to_homeFragment)
			}
			.setNegativeButton("No") { _, _ ->

			}
			.show()
	}

	override fun onDestroyView() {
		restTimer.cancel()
		exTimer?.cancel()
		tts.stop()
		super.onDestroyView()
	}

	private fun setOrUpdateDuration() {
		if (entityPresentOrNot.isEmpty()) {
			val dur = DurationEntity(
				0,
				ft.format(cal.time),
				levelName,
				levelDurationPerDay
			)
			durModel.insertDuration(dur)
		} else {
			durModel.updateDuration(ft.format(cal.time), levelDurationPerDay, levelName)
		}
	}

	private fun startTimer(index: Int, context: Context) {
		if (index == dailyList.size) {
			setOrUpdateDuration()
			findNavController().navigate(R.id.action_timerFragment_to_homeFragment)
			return
		}
		updateUi(dailyList[index])
		val ready =
			"Ready to go the next ${dailyList[index].duration} seconds ${dailyList[index].exerciseName}"
		binding.instructionText.text = ready
		speakText(ready, context)

		binding.timerProgress.max = 10
		restTimer = object : CountDownTimer(10000, 1000) {
			override fun onTick(miliTime: Long) {
				binding.timerText.text = (miliTime / 1000).toString()
				binding.timerProgress.progress = (miliTime / 1000).toInt()
			}

			override fun onFinish() {
				val temp = dailyList[index]
				val ex = ExercisesEntity(
					0,
					temp.id,
					temp.exerciseName,
					temp.detail,
					temp.duration,
					temp.img,
					ft.format(cal.time)
				)
				exerciseModel.insertExercise(ex)
				val start = "Start"
				binding.instructionText.text = start
				speakText(start, context)
				binding.timerProgress.max = dailyList[index].duration.toInt()
				exTimer = object : CountDownTimer(dailyList[index].duration * 1000, 1000) {
					override fun onTick(miliTime: Long) {
						levelDurationPerDay++
						val seconds = miliTime / 1000
						binding.timerText.text = (seconds).toString()
						binding.timerProgress.progress = (seconds).toInt()
						if (seconds == (dailyList[index].duration / 2)) {
							val halfTimeText = "Half time"
							speakText(halfTimeText, context)
						}
					}

					override fun onFinish() {
						binding.dailyProgressIndicator.progress = countEx++
						startTimer(index + 1, context)
					}
				}.start()

			}
		}.start()
	}

	private fun updateUi(exercise: Exercises) {
		Glide.with(this).load(exercise.img).into(binding.timerImageView)
		binding.exerciseNameText.text = exercise.exerciseName
	}

	private fun speakText(text: String, context: Context) {
		tts = TextToSpeech(context) {
			if (it == TextToSpeech.SUCCESS) {
				tts.language = Locale.US
				tts.setSpeechRate(1.0f)
				tts.speak(text, TextToSpeech.QUEUE_ADD, null, "")
			}
		}
	}
}