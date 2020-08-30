package com.appwiz.breakingbad.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.appwiz.breakingbad.R
import com.appwiz.breakingbad.model.EntityEpisode
import com.appwiz.breakingbad.model.Episode

class EpisodesAdapter(var episodes:List<EntityEpisode>) : RecyclerView.Adapter<RecyclerView.ViewHolder> () {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.episode_item, parent, false)
        return EpisodesViewHolder(view)
    }

    override fun getItemCount() = episodes.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val episode = episodes.get(position)
        (holder as EpisodesViewHolder).updateView(episode)
    }

    fun setData(newEpisodes:List<EntityEpisode>) {
        episodes = newEpisodes
        notifyDataSetChanged()
    }

    class EpisodesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title:TextView = itemView.findViewById(R.id.title)
        val series:TextView = itemView.findViewById(R.id.series)
        val season:TextView = itemView.findViewById(R.id.season)
        val epi:TextView = itemView.findViewById(R.id.episode)
        val air:TextView = itemView.findViewById(R.id.date)
        val chars:TextView = itemView.findViewById(R.id.chars)

        fun updateView(episode: EntityEpisode) {
            title.text = episode.title
            series.text = episode.series
            season.text = episode.season
            epi.text = episode.episode
            air.text = episode.date
            chars.text = episode.characters
        }
    }
}