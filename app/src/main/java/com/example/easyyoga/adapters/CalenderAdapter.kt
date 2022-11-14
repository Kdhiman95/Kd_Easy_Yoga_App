package com.example.easyyoga.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.easyyoga.R
import com.example.easyyoga.view_models.DurationViewModel
import com.example.easyyoga.view_models.ExerciseViewModel
import java.text.SimpleDateFormat
import java.util.*

class CalenderAdapter(
	private val list: ArrayList<String>,
	private val exModel: ExerciseViewModel,
	private val durModel: DurationViewModel
) :
	RecyclerView.Adapter<CalenderAdapter.DateViewHolder>() {

	private val cal = Calendar.getInstance()
	private val ft = SimpleDateFormat("yyyy-MM-dd", Locale.US)
	private var selectedItemPos = if(list.contains(ft.format(cal.time))){
		getTodayDateSelected()
	}else {
		-1
	}

	private var lastItemSelectedPos = if(list.contains(ft.format(cal.time))){
		getTodayDateSelected()
	}else {
		-1
	}

	private fun getTodayDateSelected(): Int {
		var index = 0
		for(i in 0 until list.size){
			if(list[i] == ft.format(cal.time)){
				index = i
			}
		}
		return index
	}

	private val days: ArrayList<String> =
		arrayListOf("SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT")

	inner class DateViewHolder(view: View) : RecyclerView.ViewHolder(view) {

		private var dateView: CardView
		private var day: TextView
		private var date: TextView

		init {
			dateView = view.findViewById(R.id.calenderDateView)
			day = view.findViewById(R.id.calenderDayText)
			date = view.findViewById(R.id.calenderDateText)

			view.setOnClickListener {
				selectedItemPos = adapterPosition
				lastItemSelectedPos = if (lastItemSelectedPos == -1)
					selectedItemPos
				else {
					notifyItemChanged(lastItemSelectedPos)
					selectedItemPos
				}
				notifyItemChanged(selectedItemPos)
				exModel.getExerciseData(list[selectedItemPos])
				durModel.getDuration(list[selectedItemPos])
			}

		}

		fun defaultBg() {
			dateView.setBackgroundResource(R.drawable.bg_capsule_unselected)
		}

		fun selectedBg() {
			dateView.setBackgroundResource(R.drawable.bg_capsule_selected)
		}

		fun bind(data: String) {
			day.text = days[adapterPosition]
			date.text = getDate(data)
		}

		private fun getDate(data: String): CharSequence =
			data.subSequence(data.length - 2, data.length)

	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
		val view =
			LayoutInflater.from(parent.context).inflate(R.layout.calender_date, parent, false)
		return DateViewHolder(view)
	}

	override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
		if (position == selectedItemPos)
			holder.selectedBg()
		else
			holder.defaultBg()

		holder.bind(list[position])
	}

	override fun getItemCount(): Int = list.size
}