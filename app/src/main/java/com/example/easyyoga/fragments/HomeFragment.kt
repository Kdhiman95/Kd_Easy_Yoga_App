package com.example.easyyoga.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.easyyoga.R
import com.example.easyyoga.adapters.LevelAdapter
import com.example.easyyoga.adapters.TipAdapter
import com.example.easyyoga.databinding.FragmentHomeBinding
import com.example.easyyoga.utils.EasyYogaApplication
import com.example.easyyoga.utils.LevelsData.Companion.levelsList
import com.example.easyyoga.view_models.DurationViewModel
import com.example.easyyoga.view_models.DurationViewModelFactory

class HomeFragment : Fragment() {

	private lateinit var binding: FragmentHomeBinding
	private lateinit var durModel: DurationViewModel

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View {
		// Inflate the layout for this fragment
		binding = FragmentHomeBinding.inflate(inflater, container, false)

		val pref = requireContext().getSharedPreferences("UserData", AppCompatActivity.MODE_PRIVATE)

		val repo = ((activity as FragmentActivity).application as EasyYogaApplication).repository
		durModel =
			ViewModelProvider(this, DurationViewModelFactory(repo))[DurationViewModel::class.java]

		binding.homeFragmentRecV.layoutManager =
			GridLayoutManager(requireContext(), 1, GridLayoutManager.HORIZONTAL, false)

		binding.tipRecV.layoutManager =
			GridLayoutManager(requireContext(), 1, GridLayoutManager.HORIZONTAL, false)

		binding.yourName.text = pref.getString("yourName", "")!!

		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val slideInRight = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in_right)
		slideInRight.duration = 500

		val fadeIn =
			AnimationUtils.loadAnimation(requireContext(), androidx.appcompat.R.anim.abc_fade_in)
		fadeIn.duration = 1000

		val fadeOut = AnimationUtils.loadAnimation(requireContext(), androidx.appcompat.R.anim.abc_fade_out)
		fadeOut.duration = 1000

		val rotateAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.rotate)
		rotateAnim.duration = 1000

		binding.greetLayout.startAnimation(slideInRight)

		binding.homeFragmentRecV.adapter =
			LevelAdapter(levelsList, requireContext(), findNavController())

		binding.tipRecV.adapter = TipAdapter(requireContext(),getTipListIndex())

		binding.reloadBtn.setOnClickListener {
			binding.reloadBtn.isCheckable = false
			binding.reloadBtn.startAnimation(rotateAnim)
			binding.tipRecV.startAnimation(fadeOut)
			Handler(Looper.getMainLooper()).postDelayed({
				binding.reloadBtn.isCheckable = true
				binding.tipRecV.startAnimation(fadeIn)
				binding.tipRecV.adapter = TipAdapter(requireContext(), getTipListIndex())
			}, 1000)
		}
	}

	private fun getTipListIndex(): ArrayList<Int>{
		val list = arrayListOf<Int>()
		while(list.size<3){
			val random = (0..10).random()
			if(list.contains(random)){
				continue
			}else{
				list.add(random)
			}
		}
		return list
	}

}