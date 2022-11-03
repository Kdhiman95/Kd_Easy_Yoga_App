package com.example.easyyoga.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.easyyoga.R

class CalenderAdapter(private val list: ArrayList<String>) :
	RecyclerView.Adapter<CalenderAdapter.DateViewHolder>() {

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
		}

		fun bind(data: String) {
			day.text = days[adapterPosition]
			date.text = getDate(data)
		}

		private fun getDate(data: String): CharSequence {
			return data.subSequence(data.length - 2, data.length)
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
		val view =
			LayoutInflater.from(parent.context).inflate(R.layout.calender_date, parent, false)
		return DateViewHolder(view)
	}

	override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
		holder.bind(list[position])
	}

	override fun getItemCount(): Int = list.size
}