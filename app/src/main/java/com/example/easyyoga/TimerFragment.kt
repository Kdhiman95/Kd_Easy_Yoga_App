package com.example.easyyoga

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.easyyoga.databinding.FragmentTimerBinding

class TimerFragment : Fragment() {

//	private lateinit var tts : TextToSpeech
//
//	enum class TimerState {
//		Stopped, Paused, Running
//	}

	private lateinit var binding: FragmentTimerBinding

//	private lateinit var timer: CountDownTimer
//	private var timerLengthSeconds = 0L
//	private var secondsRemaining = 0L
//	private var timerState = TimerState.Stopped

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View? {
		// Inflate the layout for this fragment
		binding = FragmentTimerBinding.inflate(inflater, container, false)

//		binding.timerStartAndPauseButton.setOnClickListener { v->
//			if(binding.timerStartAndPauseButton.text.toString() == "Start"){
//				binding.timerStartAndPauseButton.text = "Pause"
//				startTimer()
//				timerState = TimerState.Running
//			} else {
//				binding.timerStartAndPauseButton.text = "Start"
//				pauseTimer()
//				timerState = TimerState.Paused
//			}
//		}

//		val t = view.findViewById<TextView>(R.id.hello)

//		tts = TextToSpeech(requireContext()) {
//			if (it==TextToSpeech.SUCCESS){
//				tts.language = Locale.US
//				tts.setSpeechRate(1.0f)
//				tts.speak(t.text.toString(),TextToSpeech.QUEUE_ADD,null)
//			}
//		}

		return binding.root
	}

//	private fun startTimer() {
//		TODO("Not yet implemented")
//	}
}