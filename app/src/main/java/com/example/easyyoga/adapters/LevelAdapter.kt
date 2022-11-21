package com.example.easyyoga.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.easyyoga.R
import com.example.easyyoga.utils.Levels
import com.example.easyyoga.utils.LevelsData.Companion.levelImg
import com.example.easyyoga.utils.LevelsData.Companion.levelName

class LevelAdapter(
	private val list: ArrayList<Levels>,
	private val context: Context,
	private val navController: NavController,
) :
	RecyclerView.Adapter<LevelAdapter.LevelViewHolder>() {

	inner class LevelViewHolder(view: View) : RecyclerView.ViewHolder(view) {

		private val levelText: TextView
		private val levelImage: ImageView

		init {
			levelText = view.findViewById(R.id.levelText)
			levelImage = view.findViewById(R.id.levelImage)

			val anim = AnimationUtils.loadAnimation(context,R.anim.slide_in_right)
			anim.duration = 500
			view.startAnimation(anim)

			view.setOnClickListener {
				levelName = list[adapterPosition].title
				levelImg = list[adapterPosition].img
				navController.navigate(R.id.action_homeFragment_to_daysFragment)
			}
		}
		fun bind(levels: Levels) {
			levelText.text = levels.title
			levelImage.setImageResource(levels.img)
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.level_item, parent, false)
		return LevelViewHolder(view)
	}

	override fun onBindViewHolder(holder: LevelViewHolder, position: Int) {
		holder.bind(list[position])
	}

	override fun getItemCount(): Int = list.size
}