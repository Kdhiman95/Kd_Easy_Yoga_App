package com.example.easyyoga.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.easyyoga.R
import com.example.easyyoga.databinding.FragmentWebViewBinding

class WebViewFragment : Fragment() {
	private lateinit var viewBinding: FragmentWebViewBinding

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View {
		// Inflate the layout for this fragment
		viewBinding = FragmentWebViewBinding.inflate(inflater, container, false)
		return viewBinding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		viewBinding.webView.loadUrl("https://www.thegoodbody.com/benefits-of-yoga-for-kids/")

		viewBinding.backBtn.setOnClickListener {
			findNavController().navigate(R.id.action_webViewFragment_to_homeFragment)
		}
	}
}