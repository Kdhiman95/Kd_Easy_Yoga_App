package com.example.easyyoga

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easyyoga.adapter.CalenderAdapter
import com.example.easyyoga.databinding.FragmentReportBinding
import java.text.SimpleDateFormat
import java.util.*

class ReportFragment : Fragment() {

	private lateinit var binding: FragmentReportBinding

	private val cal = Calendar.getInstance()
	private val ft = SimpleDateFormat("yyyy-MM-dd")

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View {
		// Inflate the layout for this fragment
		binding = FragmentReportBinding.inflate(inflater, container, false)

		cal.firstDayOfWeek = Calendar.SUNDAY
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)

		binding.reportFragmentCalenderRecV.layoutManager =
			LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

		binding.reportFragmentCalenderRecV.adapter = CalenderAdapter(getCurrent())

		binding.reportFragmentNextBtn.setOnClickListener {
			binding.reportFragmentCalenderRecV.adapter = CalenderAdapter(getNext())
		}

		binding.reportFragmentPrevBtn.setOnClickListener {
			binding.reportFragmentCalenderRecV.adapter = CalenderAdapter(getPre())
		}

		return binding.root
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