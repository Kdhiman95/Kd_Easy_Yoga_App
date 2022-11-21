package com.example.easyyoga.fragments

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.easyyoga.R
import com.example.easyyoga.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

	private lateinit var binding: FragmentSplashBinding
	private lateinit var pref: SharedPreferences
	private lateinit var imm: InputMethodManager
	private lateinit var slideUpAnim: Animation
	private lateinit var slideDownAnim: Animation
	private lateinit var fadeOutAnim: Animation
	private lateinit var fadeInAnim: Animation

	private var gone = 0
	private var visible = 0
	private var gender = "Male"

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View {
		// Inflate the layout for this fragment
		binding = FragmentSplashBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		pref = requireContext().getSharedPreferences("UserData", AppCompatActivity.MODE_PRIVATE)

		imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

		//initialize animation
		slideUpAnim = AnimationUtils.loadAnimation(requireContext(),
			androidx.appcompat.R.anim.abc_slide_in_bottom)
		fadeOutAnim =
			AnimationUtils.loadAnimation(requireContext(), androidx.appcompat.R.anim.abc_fade_out)
		slideDownAnim = AnimationUtils.loadAnimation(requireContext(),
			androidx.appcompat.R.anim.abc_slide_in_top)
		fadeInAnim =
			AnimationUtils.loadAnimation(requireContext(), androidx.appcompat.R.anim.abc_fade_in)

		//initialize visibility
		gone = View.GONE
		visible = View.VISIBLE

		//initializing number picker values
		binding.weightKgPicker.minValue = 5
		binding.weightKgPicker.maxValue = 150
		binding.weightKgPicker.value = 58

		binding.heightFtPicker.minValue = 3
		binding.heightFtPicker.maxValue = 10
		binding.heightFtPicker.value = 5

		binding.heightInPicker.minValue = 0
		binding.heightInPicker.maxValue = 11
		binding.heightInPicker.value = 7

		binding.caloriesPicker.minValue = 5
		binding.caloriesPicker.maxValue = 150
		binding.caloriesPicker.value = 10

		//check that user detail is filled
		val loggedIn = pref.getBoolean("LoggedIn", false)
		if (loggedIn) {
			Handler(Looper.getMainLooper()).postDelayed({
				findNavController().navigate(R.id.action_splashFragment_to_navigationFragment)
			}, 1000)
		} else {
			askUser(requireContext())
		}

	}


	private fun askUser(context: Context) {
		val editor = pref.edit()

		//delay for get start button
		Handler(Looper.getMainLooper()).postDelayed({
			binding.getStartBtn.visibility = visible
			binding.getStartBtn.startAnimation(slideUpAnim)
		}, 1000)

		//get start button click
		binding.getStartBtn.setOnClickListener {
			binding.firstLayout.startAnimation(fadeOutAnim)
			binding.firstLayout.visibility = gone
			binding.enterYourNameLayout.visibility = visible
			binding.enterYourNameLayout.startAnimation(slideUpAnim)
			binding.nameBackBtn.isClickable = true
			binding.getStartBtn.isClickable = false
		}

		//name layout back button click
		binding.nameBackBtn.setOnClickListener {
			binding.enterYourNameLayout.startAnimation(fadeOutAnim)
			binding.enterYourNameLayout.visibility = gone
			binding.firstLayout.visibility = visible
			binding.firstLayout.startAnimation(slideDownAnim)
			binding.nameBackBtn.isClickable = false
			binding.getStartBtn.isClickable = true
			imm.hideSoftInputFromWindow(it.windowToken, 0)
		}

		//name save button click
		binding.nameSaveBtn.setOnClickListener {
			if (binding.yourNameEditText.text!!.isNotBlank()) {
				binding.enterYourNameLayout.startAnimation(fadeOutAnim)
				binding.enterYourNameLayout.visibility = gone
				binding.wHLayout.visibility = visible
				binding.wHLayout.startAnimation(slideUpAnim)
				binding.wHBackBtn.isClickable = true
				binding.nameSaveBtn.isClickable = false
				imm.hideSoftInputFromWindow(it.windowToken, 0)
				//save name in preference TODO
				editor.putString("yourName", binding.yourNameEditText.text.toString())
				editor.putString("gender",gender)
				editor.apply()
			} else {
				binding.yourNameEditTextLayout.error = "please enter your name!!"
			}

		}

		//wh back button click
		binding.wHBackBtn.setOnClickListener {
			binding.wHLayout.startAnimation(fadeOutAnim)
			binding.wHLayout.visibility = gone
			binding.enterYourNameLayout.visibility = visible
			binding.enterYourNameLayout.startAnimation(slideDownAnim)
			binding.wHBackBtn.isClickable = false
			binding.nameSaveBtn.isClickable = true
			imm.hideSoftInputFromWindow(it.windowToken, 0)
		}

		//wh save button click
		binding.wHSaveBtn.setOnClickListener {
			binding.wHLayout.startAnimation(fadeOutAnim)
			binding.wHLayout.visibility = gone
			binding.caloriesLayout.visibility = visible
			binding.caloriesLayout.startAnimation(slideUpAnim)
			binding.wHSaveBtn.isClickable = false
			binding.caloriesBackBtn.isClickable = true
			imm.hideSoftInputFromWindow(it.windowToken, 0)
			//save weight and height in preferences TODO
			editor.putString("weight", binding.weightKgPicker.value.toString())
			editor.putString("heightFeet", binding.heightFtPicker.value.toString())
			editor.putString("heightIn", binding.heightInPicker.value.toString())
			editor.apply()
		}

		//calories back button click
		binding.caloriesBackBtn.setOnClickListener {
			binding.caloriesLayout.startAnimation(fadeOutAnim)
			binding.caloriesLayout.visibility = gone
			binding.wHLayout.visibility = visible
			binding.wHLayout.startAnimation(slideDownAnim)
			binding.caloriesBackBtn.isClickable = false
			binding.wHSaveBtn.isClickable = true
			imm.hideSoftInputFromWindow(it.windowToken, 0)
		}

		//calories save button click
		binding.caloriesSaveBtn.setOnClickListener {
			binding.caloriesLayout.startAnimation(fadeOutAnim)
			binding.caloriesLayout.visibility = gone
			binding.allDoneLayout.visibility = visible
			binding.allDoneLayout.startAnimation(slideUpAnim)
			binding.caloriesSaveBtn.isClickable = false
			imm.hideSoftInputFromWindow(it.windowToken, 0)
			binding.successImageView.visibility = visible
			Glide.with(context).load(R.drawable.success).into(binding.successImageView)
			editor.putString("calories", binding.caloriesPicker.value.toString())
			editor.putBoolean("LoggedIn", true)
			editor.apply()
			Handler(Looper.getMainLooper()).postDelayed({
				findNavController().navigate(R.id.action_splashFragment_to_navigationFragment)
			}, 2000)
		}

		//male button click
		binding.maleBtn.setOnClickListener {
			gender = "Male"
			binding.femaleBtn.setBackgroundColor(Color.WHITE)
			binding.femaleBtn.setTextColor(Color.BLACK)
			binding.maleBtn.setBackgroundColor(Color.BLACK)
			binding.maleBtn.setTextColor(Color.WHITE)
		}

		//female button click
		binding.femaleBtn.setOnClickListener {
			gender = "Female"
			binding.maleBtn.setBackgroundColor(Color.WHITE)
			binding.maleBtn.setTextColor(Color.BLACK)
			binding.femaleBtn.setBackgroundColor(Color.BLACK)
			binding.femaleBtn.setTextColor(Color.WHITE)
		}
	}
}