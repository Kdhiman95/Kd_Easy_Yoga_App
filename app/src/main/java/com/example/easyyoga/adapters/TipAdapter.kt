package com.example.easyyoga.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.easyyoga.R

class TipAdapter(
	private val context: Context,
	private val list: ArrayList<Int>,
	private val navController: NavController,
) :
	RecyclerView.Adapter<TipAdapter.TipViewHolder>() {

	private val tipList = arrayOf(
		"IT’S A FUN WAY TO RELAX",
		"HELPS IMPROVE THEIR ATTENTION SPAN",
		"ENCOURAGES THE CONNECTION BETWEEN BODY AND MIND",
		"GOOD FOR BALANCE",
		"INCREASES FLEXIBILITY",
		"BUILDS STRENGTH",
		"IMPROVES THEIR MENTAL WELLBEING",
		"BOOSTS IMMUNITY",
		"ENCOURAGES HEALTHY EATING",
		"REDUCES STRESS AND ANXIETY",
		"LEADS TO BETTER SLEEP",
		"IMPROVES SELF-ESTEEM",
		"ENCOURAGES EMPATHY",
		"IT’S NOT COMPETITIVE",
		"BUILDS CONFIDENCE",
		"INTRODUCES KIDS TO MINDFULNESS",
		"INCREASES ENERGY LEVELS",
		"HELPS WITH SELF-CONTROL",
		"IMPROVES BEHAVIOR",
		"CAN HELP TO IMPROVE MEMORY"
	)

	inner class TipViewHolder(view: View) : RecyclerView.ViewHolder(view) {

		private val tipText: TextView

		init {
			tipText = view.findViewById(R.id.tipText)

			val slideInRight = AnimationUtils.loadAnimation(context, R.anim.slide_in_right)
			slideInRight.duration = 500

			view.startAnimation(slideInRight)

			view.setOnClickListener {
				navController.navigate(R.id.action_homeFragment_to_webViewFragment)
			}
		}

		fun bind(index: Int) {
			tipText.text = tipList[index]
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.tip, parent, false)
		return TipViewHolder(view)
	}

	override fun onBindViewHolder(holder: TipViewHolder, position: Int) {
		holder.bind(list[position])
	}

	override fun getItemCount(): Int = list.size
}