package com.example.easyyoga

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class TimerFragment : Fragment() {

//	private lateinit var tts : TextToSpeech

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View? {
		// Inflate the layout for this fragment
		val view = inflater.inflate(R.layout.fragment_timer, container, false)

//		val t = view.findViewById<TextView>(R.id.hello)

//		tts = TextToSpeech(requireContext()) {
//			if (it==TextToSpeech.SUCCESS){
//				tts.language = Locale.US
//				tts.setSpeechRate(1.0f)
//				tts.speak(t.text.toString(),TextToSpeech.QUEUE_ADD,null)
//			}
//		}

		return view
	}
}