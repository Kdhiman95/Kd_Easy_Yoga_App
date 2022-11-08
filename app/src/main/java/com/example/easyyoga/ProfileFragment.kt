package com.example.easyyoga

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.easyyoga.databinding.FragmentProfileBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class ProfileFragment : Fragment() {

	private lateinit var binding: FragmentProfileBinding

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View {
		// Inflate the layout for this fragment
		binding = FragmentProfileBinding.inflate(inflater, container, false)

		val pref = requireContext().getSharedPreferences("UserData",AppCompatActivity.MODE_PRIVATE)

		binding.weightText.text = pref.getString("weight",null)
		binding.feetText.text = pref.getString("heightFeet",null)
		binding.inchText.text = pref.getString("heightIn",null)

		binding.weightEdit.setOnClickListener {
			val dialog = Dialog(requireContext())
			dialog.setContentView(R.layout.weight_dialog_box)

			val weight = dialog.findViewById<TextInputEditText>(R.id.dialogWeightEdittext)
			val saveBtn = dialog.findViewById<MaterialButton>(R.id.dialogWeightSaveBtn)

			weight.setText(binding.weightText.text)

			saveBtn.setOnClickListener {
				val editor = pref.edit()
				binding.weightText.text = weight.text
				editor.putString("weight", weight.text.toString())
				editor.apply()
				dialog.dismiss()
			}
			dialog.show()
		}

		binding.heightEdit.setOnClickListener {
			val dialog = Dialog(requireContext())
			dialog.setContentView(R.layout.height_dialog_box)
			val feet = dialog.findViewById<TextInputEditText>(R.id.dialogFeetText)
			val inch = dialog.findViewById<TextInputEditText>(R.id.dialogInchText)
			val saveBtn = dialog.findViewById<MaterialButton>(R.id.dialogHeightSaveBtn)

			feet.setText(binding.feetText.text)
			inch.setText(binding.inchText.text)

			saveBtn.setOnClickListener {
				val editor = pref.edit()
				binding.feetText.text = feet.text
				binding.inchText.text = inch.text
				editor.putString("heightFeet",feet.text.toString())
				editor.putString("heightIn",inch.text.toString())
				editor.apply()
				dialog.dismiss()
			}
			dialog.show()
		}

		return binding.root
	}
}