package com.example.easyyoga

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

		binding.weightText.text = "58"
		binding.feetText.text = "5"
		binding.inchText.text = "7"

		binding.weightEdit.setOnClickListener {
			val dialog = Dialog(requireContext())
			dialog.setContentView(R.layout.weight_dialog_box)

			val weight = dialog.findViewById<TextInputEditText>(R.id.dialogWeightEdittext)
			val saveBtn = dialog.findViewById<MaterialButton>(R.id.dialogButton)

			weight.setText(binding.weightText.text)

			saveBtn.setOnClickListener {
				binding.weightText.text = weight.text
				dialog.dismiss()
			}

			dialog.show()
		}

		binding.heightEdit.setOnClickListener {

		}

		return binding.root
	}
}