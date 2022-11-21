package com.example.easyyoga.fragments

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.Navigation
import com.example.easyyoga.R
import com.example.easyyoga.databinding.FragmentProfileBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView

class ProfileFragment : Fragment() {

	private lateinit var name: String
	private lateinit var binding: FragmentProfileBinding
	private lateinit var pref: SharedPreferences
	private lateinit var calGoal: String
	private lateinit var heightIn: String
	private lateinit var heightFt: String
	private lateinit var weightT: String
	private lateinit var gender: String

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View {
		// Inflate the layout for this fragment
		binding = FragmentProfileBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		pref = requireContext().getSharedPreferences("UserData", AppCompatActivity.MODE_PRIVATE)
		val editor = pref.edit()

		setData()

		var goalsIsVisible = false
		binding.goalsCardView.setOnClickListener {
			goalsIsVisible = !goalsIsVisible
			if (goalsIsVisible) {
				binding.goalLayout.visibility = View.VISIBLE
				binding.goalImageView.setImageResource(R.drawable.ic_round_keyboard_arrow_up_24)
			} else {
				binding.goalLayout.visibility = View.GONE
				binding.goalImageView.setImageResource(R.drawable.ic_round_keyboard_arrow_down_24)
			}
		}

		var whIsVisible = false
		binding.whCardView.setOnClickListener {
			whIsVisible = !whIsVisible
			if (whIsVisible) {
				binding.whLayout.visibility = View.VISIBLE
				binding.whImageView.setImageResource(R.drawable.ic_round_keyboard_arrow_up_24)
			} else {
				binding.whLayout.visibility = View.GONE
				binding.whImageView.setImageResource(R.drawable.ic_round_keyboard_arrow_down_24)
			}
		}

		binding.nameEdit.setOnClickListener {

			val dialog = Dialog(requireContext())
			dialog.setContentView(R.layout.edit_name_dialog)
			dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

			val valueText = dialog.findViewById<TextInputEditText>(R.id.nameDialogEditText)
			val saveBtn = dialog.findViewById<MaterialButton>(R.id.nameDialogSaveBtn)
			val maleBtn = dialog.findViewById<ExtendedFloatingActionButton>(R.id.maleBtn)
			val femaleBtn = dialog.findViewById<ExtendedFloatingActionButton>(R.id.femaleBtn)

			when (gender) {
				"Male" -> {
					femaleBtn.setBackgroundColor(Color.WHITE)
					femaleBtn.setTextColor(Color.BLACK)
					maleBtn.setBackgroundColor(Color.BLACK)
					maleBtn.setTextColor(Color.WHITE)
				}
				"Female" -> {
					maleBtn.setBackgroundColor(Color.WHITE)
					maleBtn.setTextColor(Color.BLACK)
					femaleBtn.setBackgroundColor(Color.BLACK)
					femaleBtn.setTextColor(Color.WHITE)
				}
			}

			maleBtn.setOnClickListener {
				gender = "Male"
				femaleBtn.setBackgroundColor(Color.WHITE)
				femaleBtn.setTextColor(Color.BLACK)
				maleBtn.setBackgroundColor(Color.BLACK)
				maleBtn.setTextColor(Color.WHITE)
			}

			femaleBtn.setOnClickListener {
				gender = "Female"
				maleBtn.setBackgroundColor(Color.WHITE)
				maleBtn.setTextColor(Color.BLACK)
				femaleBtn.setBackgroundColor(Color.BLACK)
				femaleBtn.setTextColor(Color.WHITE)
			}

			valueText.setText(name)

			saveBtn.setOnClickListener {
				editor.putString("yourName", valueText.text.toString())
				editor.putString("gender", gender)
				editor.apply()
				setData()
				dialog.dismiss()
			}
			dialog.show()
		}

		binding.goalEdit.setOnClickListener {
			showDialog(requireContext(), "Calories", "Kcal")
		}

		binding.weightEdit.setOnClickListener {
			showDialog(requireContext(), "Weight", "Kg")
		}

		binding.heightEdit.setOnClickListener {
			val dialog = Dialog(requireContext())
			dialog.setContentView(R.layout.height_dialog_box)
			dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

			val heightFtPicker = dialog.findViewById<NumberPicker>(R.id.heightFtPicker)
			val heightInPicker = dialog.findViewById<NumberPicker>(R.id.heightInPicker)
			val saveBtn =
				dialog.findViewById<ExtendedFloatingActionButton>(R.id.dialogHeightSaveBtn)

			heightFtPicker.minValue = 3
			heightFtPicker.maxValue = 10
			heightFtPicker.value = heightFt.toInt()

			heightInPicker.minValue = 0
			heightInPicker.maxValue = 11
			heightInPicker.value = heightIn.toInt()

			saveBtn.setOnClickListener {
				editor.putString("heightFeet", heightFtPicker.value.toString())
				editor.putString("heightIn", heightInPicker.value.toString())
				editor.apply()
				setData()
				dialog.dismiss()
			}
			dialog.show()
		}

		binding.signOutBtn.setOnClickListener {
			editor.putBoolean("LoggedIn", false)
			editor.apply()
			val mainNav =
				requireActivity().findViewById<FragmentContainerView>(R.id.startFragmentView)
			Navigation.findNavController(mainNav)
				.navigate(R.id.action_navigationFragment_to_splashFragment)
		}
	}

	private fun setData() {
		name = pref.getString("yourName", "")!!
		weightT = pref.getString("weight", "")!!
		heightFt = pref.getString("heightFeet", "")!!
		heightIn = pref.getString("heightIn", "")!!
		calGoal = pref.getString("calories", "")!!
		gender = pref.getString("gender", "")!!

		if (gender == "Male") {
			binding.profileImage.setImageResource(R.drawable.intermediate_image)
		} else {
			binding.profileImage.setImageResource(R.drawable.beginner_image)
		}

		binding.userNameText.text = name
		binding.weightText.text = weightT
		binding.feetText.text = heightFt
		binding.inchText.text = heightIn
		binding.goalText.text = calGoal
	}

	private fun showDialog(context: Context, title: String, unit: String) {
		val dialog = Dialog(context)
		dialog.setContentView(R.layout.common_dailog)

		val titleT = dialog.findViewById<MaterialTextView>(R.id.dialogTitle)
		val numberPicker = dialog.findViewById<NumberPicker>(R.id.numberPicker)
		val unitt = dialog.findViewById<MaterialTextView>(R.id.unitText)
		val saveBtn = dialog.findViewById<ExtendedFloatingActionButton>(R.id.dialogSaveBtn)
		dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

		titleT.text = title
		unitt.text = unit

		when (title) {
			"Weight" -> {
				numberPicker.minValue = 5
				numberPicker.maxValue = 150
				numberPicker.value = weightT.toInt()
			}
			"Calories" -> {
				numberPicker.minValue = 5
				numberPicker.maxValue = 150
				numberPicker.value = calGoal.toInt()
			}
		}

		saveBtn.setOnClickListener {
			val editor = pref.edit()
			when (title) {
				"Weight" -> {
					editor.putString("weight", numberPicker.value.toString())
				}
				"Calories" -> {
					editor.putString("calories", numberPicker.value.toString())
				}
			}
			editor.apply()
			setData()
			dialog.dismiss()
		}
		dialog.show()
	}
}