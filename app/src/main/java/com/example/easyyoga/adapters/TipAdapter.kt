package com.example.easyyoga.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.easyyoga.R

class TipAdapter(private val context: Context, private val list: ArrayList<Int>) :
	RecyclerView.Adapter<TipAdapter.TipViewHolder>() {

	inner class TipViewHolder(view: View) : RecyclerView.ViewHolder(view) {

		private val tipText : TextView

		init {
			tipText = view.findViewById(R.id.tipText)

			val slideInRight = AnimationUtils.loadAnimation(context, R.anim.slide_in_right)
			slideInRight.duration = 500

			view.startAnimation(slideInRight)
		}

		fun bind(index: Int) {
			tipText.text = index.toString()
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.tip,parent,false)
		return TipViewHolder(view)
	}

	override fun onBindViewHolder(holder: TipViewHolder, position: Int) {
		holder.bind(list[position])
	}

	override fun getItemCount(): Int = list.size
}