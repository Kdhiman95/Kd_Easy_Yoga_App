package com.example.easyyoga.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.easyyoga.R
import com.example.easyyoga.utils.LevelData

class LevelAdapter(private val list: ArrayList<LevelData>) :
	RecyclerView.Adapter<LevelAdapter.LevelViewHolder>() {

	inner class LevelViewHolder(view: View) : RecyclerView.ViewHolder(view) {

		val levelText : TextView
		val levelImage : ImageView

		init {
			levelText = view.findViewById(R.id.levelText)
			levelImage = view.findViewById(R.id.levelImage)
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