package com.example.easyyoga.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easyyoga.adapters.CalenderAdapter
import com.example.easyyoga.adapters.ExerciseAdapter
import com.example.easyyoga.databinding.FragmentReportBinding
import com.example.easyyoga.model.room_database.ExercisesEntity
import com.example.easyyoga.utils.EasyYogaApplication
import com.example.easyyoga.view_models.DurationViewModel
import com.example.easyyoga.view_models.DurationViewModelFactory
import com.example.easyyoga.view_models.ExerciseViewModel
import com.example.easyyoga.view_models.ExerciseViewModelFactory
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class ReportFragment : Fragment() {

	private lateinit var binding: FragmentReportBinding
	private lateinit var exModel: ExerciseViewModel
	private lateinit var durModel: DurationViewModel

	private val cal = Calendar.getInstance()
	private val ft = SimpleDateFormat("yyyy-MM-dd", Locale.US)

	private val presentCal = Calendar.getInstance()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View {
		// Inflate the layout for this fragment
		binding = FragmentReportBinding.inflate(inflater, container, false)

		val repo = ((activity as FragmentActivity).application as EasyYogaApplication).repository

		exModel =
			ViewModelProvider(this, ExerciseViewModelFactory(repo))[ExerciseViewModel::class.java]

		durModel =
			ViewModelProvider(this, DurationViewModelFactory(repo))[DurationViewModel::class.java]

		cal.firstDayOfWeek = Calendar.SUNDAY
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)

		binding.reportFragmentCalenderRecV.layoutManager =
			LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

		binding.reportFragmentExerciseRecV.layoutManager =
			LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

		binding.reportFragmentCalenderRecV.adapter =
			CalenderAdapter(getCurrent(), exModel, durModel)

		binding.reportFragmentNextBtn.setOnClickListener {
			binding.reportFragmentCalenderRecV.adapter =
				CalenderAdapter(getNext(), exModel, durModel)
		}

		binding.reportFragmentPrevBtn.setOnClickListener {
			binding.reportFragmentCalenderRecV.adapter =
				CalenderAdapter(getPre(), exModel, durModel)
		}

		return binding.root
	}

	override fun onResume() {
		val pref = requireContext().getSharedPreferences("UserData", AppCompatActivity.MODE_PRIVATE)

		val weight = pref.getString("weight", null)!!.toFloat()
		val feet = pref.getString("heightFeet", null)!!.toFloat()
		val inch = pref.getString("heightIn", null)!!.toFloat()

		val finalInch = (feet * 12) + inch

		val heightM = (finalInch * 2.54) / 100

		val bmi = (weight / (heightM * heightM))

		binding.bmiText.text = ((bmi * 100.0).roundToInt() / 100.0).toString()

		durModel.getDuration(ft.format(presentCal.time))
		durModel.durList.observe(viewLifecycleOwner) {
			if (it.isNotEmpty()) {
				binding.totalDurationText.text = it[0].totalDuration.toString()
			} else {
				binding.totalDurationText.text = (0).toString()
			}
			val totalDuration = binding.totalDurationText.text.toString().toLong()
			val cal = totalDuration * 0.06

			binding.totalCaloriesText.text = ((cal * 100.0).roundToInt() / 100.0).toString()

		}

		exModel.getExerciseData(ft.format(presentCal.time))
		exModel.exDataList.observe(viewLifecycleOwner) {
			binding.totalWorkoutsText.text = it.size.toString()

			val list: ArrayList<ExercisesEntity> = getFinalList(it)

			if (list.isEmpty()) {
				binding.noRecordText.visibility = View.VISIBLE
				binding.reportFragmentExerciseRecV.visibility = View.GONE
			} else {
				binding.noRecordText.visibility = View.GONE
				binding.reportFragmentExerciseRecV.visibility = View.VISIBLE
				binding.reportFragmentExerciseRecV.adapter = ExerciseAdapter(list, requireContext())
			}
		}

		super.onResume()
	}

	private fun getFinalList(it: List<ExercisesEntity>): ArrayList<ExercisesEntity> {
		val list: ArrayList<ExercisesEntity> = arrayListOf()
		for (ex in it) {
			if (!listContainEx(list, ex.exId)) {
				list.add(ex)
			}
		}
		return list
	}

	private fun listContainEx(list: ArrayList<ExercisesEntity>, exId: Int): Boolean {
		for (i in list) {
			if (i.exId == exId)
				return true
		}
		return false
	}

	private fun getCurrent(): ArrayList<String> {
		val dates: ArrayList<String> = arrayListOf()

		val temp = cal.time

		for (i in 0..6) {
			dates.add(ft.format(cal.time))
			cal.add(Calendar.DATE, 1)
		}

		cal.time = temp
		return dates
	}

	private fun getNext(): ArrayList<String> {
		cal.add(Calendar.DATE, +7)
		return getCurrent()
	}

	private fun getPre(): ArrayList<String> {
		cal.add(Calendar.DATE, -7)
		return getCurrent()
	}
}