package com.example.easyyoga

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.easyyoga.databinding.FragmentNavigationBinding


class NavigationFragment : Fragment() {

	private lateinit var binding: FragmentNavigationBinding
	private lateinit var navController: NavController
	private lateinit var navHostFragment: NavHostFragment

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View {
		// Inflate the layout for this fragment
		binding = FragmentNavigationBinding.inflate(inflater, container, false)

		navHostFragment =
			childFragmentManager.findFragmentById(R.id.mainNavigationFragment) as NavHostFragment

		navController = navHostFragment.navController

		setupWithNavController(binding.mainBottomNavigation, navController)

		return binding.root
	}

}