package com.example.easyyoga.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.easyyoga.R
import com.example.easyyoga.utils.LevelData

class LevelAdapter(
	private val list: ArrayList<LevelData>,
	private val navController: NavController,
) :
	RecyclerView.Adapter<LevelAdapter.LevelViewHolder>() {

	inner class LevelViewHolder(view: View) : RecyclerView.ViewHolder(view) {

		private val level: CardView
		val levelText: TextView
		val levelImage: ImageView

		init {
			level = view.findViewById(R.id.levelSelected)
			levelText = view.findViewById(R.id.levelText)
			levelImage = view.findViewById(R.id.levelImage)

			level.setOnClickListener {
				val bundle = Bundle()
				bundle.putString("Plan", list[adapterPosition].title)
				bundle.putInt("Img", list[adapterPosition].img)
				navController.navigate(R.id.action_homeFragment_to_daysFragment, bundle)
			}

		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.level_item, parent, false)
		return LevelViewHolder(view)
	}

	override fun onBindViewHolder(holder: LevelViewHolder, position: Int) {
		holder.levelText.text = list[position].title
		holder.levelImage.setImageResource(list[position].img)
	}

	override fun getItemCount(): Int = list.size
}