package com.example.easyyoga.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easyyoga.adapters.CalenderAdapter
import com.example.easyyoga.adapters.ExerciseAdapter
import com.example.easyyoga.databinding.FragmentReportBinding
import com.example.easyyoga.model.room_database.DurationEntity
import com.example.easyyoga.model.room_database.ExercisesEntity
import com.example.easyyoga.utils.Constant.Companion.cal
import com.example.easyyoga.utils.Constant.Companion.ft
import com.example.easyyoga.utils.EasyYogaApplication
import com.example.easyyoga.utils.ProgressBarAnimation
import com.example.easyyoga.view_models.DurationViewModel
import com.example.easyyoga.view_models.DurationViewModelFactory
import com.example.easyyoga.view_models.ExerciseViewModel
import com.example.easyyoga.view_models.ExerciseViewModelFactory
import java.util.*
import kotlin.math.roundToInt


class ReportFragment : Fragment() {

	private lateinit var binding: FragmentReportBinding
	private lateinit var exModel: ExerciseViewModel
	private lateinit var durModel: DurationViewModel

	private val weekCal = Calendar.getInstance()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View {
		// Inflate the layout for this fragment
		binding = FragmentReportBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val repo = ((activity as FragmentActivity).application as EasyYogaApplication).repository

		exModel =
			ViewModelProvider(this, ExerciseViewModelFactory(repo))[ExerciseViewModel::class.java]

		durModel =
			ViewModelProvider(this, DurationViewModelFactory(repo))[DurationViewModel::class.java]

		weekCal.firstDayOfWeek = Calendar.SUNDAY
		weekCal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)

		binding.reportFragmentCalenderRecV.layoutManager = GridLayoutManager(requireContext(), 7)

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


		setBmi(requireContext())

		durModel.getDuration(ft.format(cal.time))
		var duration: Long
		durModel.durList.observe(viewLifecycleOwner) {
			if (it.isNotEmpty()) {
				duration = getDur(it)
				val min = duration / 60
				val sec = duration % 60
				val totalD = if (min < 10 && sec < 10) {
					"0$min:0$sec"
				} else if (sec < 10) {
					"$min:0$sec"
				} else if (min < 10) {
					"0$min:$sec"
				} else {
					"$min:$sec"
				}
				binding.totalDurationText.text = totalD
			} else {
				binding.totalDurationText.text = (0).toString()
				duration = 0L
			}
			val totalDuration = duration.toString().toLong()
			val calories = totalDuration * 0.06

			binding.totalCaloriesText.text = ((calories * 100.0).roundToInt() / 100.0).toString()

			val anim =
				ProgressBarAnimation(binding.caloriesIndicator, 0F * 100, calories.toFloat() * 1000)
			anim.duration = 1000

			binding.caloriesIndicator.startAnimation(anim)

		}

		exModel.getExerciseData(ft.format(cal.time))
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

	}

	private fun setBmi(requireContext: Context) {
		val pref = requireContext.getSharedPreferences("UserData", AppCompatActivity.MODE_PRIVATE)

		val weight = pref.getString("weight", null)!!.toFloat()
		val feet = pref.getString("heightFeet", null)!!.toFloat()
		val inch = pref.getString("heightIn", null)!!.toFloat()

		val finalInch = (feet * 12) + inch

		val heightM = (finalInch * 2.54) / 100

		val bmi = (weight / (heightM * heightM))

		binding.bmiText.text = ((bmi * 100.0).roundToInt() / 100.0).toString()

		if (bmi <= 18.5) {
			binding.bmiText.setTextColor(Color.parseColor("#00008B"))
			binding.bmiProgressBar.setIndicatorColor(Color.parseColor("#00008B"))
		} else if (bmi >= 25) {
			binding.bmiText.setTextColor(Color.parseColor("#8B0000"))
			binding.bmiProgressBar.setIndicatorColor(Color.parseColor("#8B0000"))
		} else {
			binding.bmiText.setTextColor(Color.parseColor("#028745"))
			binding.bmiProgressBar.setIndicatorColor(Color.parseColor("#028745"))
		}
		binding.bmiProgressBar.max = 40 * 1000

		val anim = ProgressBarAnimation(binding.bmiProgressBar, 0F * 100, bmi.toFloat() * 1000)
		anim.duration = 1000

		binding.bmiProgressBar.startAnimation(anim)

		binding.caloriesIndicator.max = pref.getString("calories", "5")!!.toInt() * 1000
	}

	private fun getDur(it: List<DurationEntity>): Long {
		var dur = 0L
		for (i in it) {
			dur += i.totalDuration
		}
		return dur
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

		val temp = weekCal.time

		for (i in 0..6) {
			dates.add(ft.format(weekCal.time))
			weekCal.add(Calendar.DATE, 1)
		}

		weekCal.time = temp
		return dates
	}

	private fun getNext(): ArrayList<String> {
		weekCal.add(Calendar.DATE, +7)
		return getCurrent()
	}

	private fun getPre(): ArrayList<String> {
		weekCal.add(Calendar.DATE, -7)
		return getCurrent()
	}
}