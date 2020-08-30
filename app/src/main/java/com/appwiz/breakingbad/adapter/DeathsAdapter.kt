package com.appwiz.breakingbad.adapter

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.appwiz.breakingbad.R
import com.appwiz.breakingbad.model.Death

class DeathsAdapter(var deaths:List<Death>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.death_item, parent, false)
        return DeathsViewHolder(view)
    }

    override fun getItemCount() = deaths.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val death = deaths.get(position)
        (holder as DeathsViewHolder).updateView(death)
    }

    fun setData(newDeaths:List<Death>) {
        deaths = newDeaths
        notifyDataSetChanged()
    }

    class DeathsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val death:TextView = itemView.findViewById(R.id.death)
        val cause:TextView = itemView.findViewById(R.id.cause)
        val resp:TextView = itemView.findViewById(R.id.responsible)
        val last:TextView = itemView.findViewById(R.id.last_words)
        val season:TextView = itemView.findViewById(R.id.season)
        val episode:TextView = itemView.findViewById(R.id.episode)
        val numb:TextView = itemView.findViewById(R.id.death_number)

        fun updateView(deathItem:Death) {
            death.text = deathItem.death
            cause.text = deathItem.cause
            resp.text = deathItem.responsible
            last.text = deathItem.lastWords
            season.text = deathItem.season.toString()
            episode.text = deathItem.episode.toString()
            numb.text = deathItem.nos.toString()
        }
    }
}