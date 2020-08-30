package com.appwiz.breakingbad.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.appwiz.breakingbad.R
import com.appwiz.breakingbad.model.Character
import com.appwiz.breakingbad.model.EntityCharacter
import com.squareup.picasso.Picasso

class CharactersAdapter(private val context: Context, var characters: List<EntityCharacter>) : RecyclerView.Adapter<RecyclerView.ViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.character_item, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val character = characters.get(position)
        (holder as CharacterViewHolder).updateView(context, character)
    }

    override fun getItemCount() = characters.size

    fun setData(chars:List<EntityCharacter>) {
        characters = chars
        notifyDataSetChanged()
    }

    class CharacterViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        val name:TextView = itemView.findViewById(R.id.name)
        val bday:TextView = itemView.findViewById(R.id.bday)
        val occu:TextView = itemView.findViewById(R.id.occupation)
        val status:TextView = itemView.findViewById(R.id.status)
        val nick:TextView = itemView.findViewById(R.id.nickname)
        val appr:TextView = itemView.findViewById(R.id.appearance)
        val port:TextView = itemView.findViewById(R.id.portrayed)
        val propic:ImageView = itemView.findViewById(R.id.pro_pic)
        val bcsAppr:TextView = itemView.findViewById(R.id.appr_bcs)
        val cat:TextView = itemView.findViewById(R.id.category)

        fun updateView(context: Context, character: EntityCharacter) {
            name.text = "${context.resources.getString(R.string.chitem_name)} ${character.name}"
            bday.text = "${context.resources.getString(R.string.chitem_bday)} ${character.birthday}"
            occu.text = "${context.resources.getString(R.string.chitem_ocp)} ${character.occupation}"
            appr.text = "${context.resources.getString(R.string.chitem_appr)} ${character.appearance}"
            status.text = "${context.resources.getString(R.string.chitem_status)} ${character.status}"
            nick.text = "${context.resources.getString(R.string.chitem_nick)} ${character.nickname}"
            port.text = "${context.resources.getString(R.string.chitem_port)} ${character.portrayed}"
            cat.text = "${context.resources.getString(R.string.chitem_cat)} ${character.category}"
            bcsAppr.text = "${context.resources.getString(R.string.chitem_bcs)} ${character.appearanceBCS}"
            Picasso.get().load(character.image).fit().centerCrop()
                .placeholder(R.drawable.ic_empty).into(propic)
        }
    }
}