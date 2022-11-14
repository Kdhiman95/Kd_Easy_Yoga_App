package com.example.easyyoga.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.easyyoga.R

class SplashFragment : Fragment() {
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View? {
		// Inflate the layout for this fragment
		val view = inflater.inflate(R.layout.fragment_splash, container, false)

		Handler(Looper.getMainLooper()).postDelayed({
			findNavController().navigate(R.id.action_splashFragment_to_navigationFragment)
		}, 2000)

		return view
	}
}