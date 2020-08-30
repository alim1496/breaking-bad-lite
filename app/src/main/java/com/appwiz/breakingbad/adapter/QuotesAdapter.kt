package com.appwiz.breakingbad.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.appwiz.breakingbad.R
import com.appwiz.breakingbad.model.Quote
import com.appwiz.breakingbad.utils.CommonUtils

class QuotesAdapter(val context: Context, var quotes:List<Quote>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.quote_item, parent, false)
        return QuotesViewHolder(view)
    }

    override fun getItemCount() = quotes.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val quote = quotes.get(position)
        (holder as QuotesViewHolder).updateView(context, quote)
    }

    fun setData(newQuotes:List<Quote>) {
        quotes = newQuotes
        notifyDataSetChanged()
    }

    class QuotesViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        val quote:TextView = itemView.findViewById(R.id.quote)
        val author:TextView = itemView.findViewById(R.id.author)
        val series:TextView = itemView.findViewById(R.id.series)

        val fbShare: ImageView = itemView.findViewById(R.id.fb_icon)
        val msgShare: ImageView = itemView.findViewById(R.id.msg_icon)
        val twtShare: ImageView = itemView.findViewById(R.id.twt_icon)
        val whapShare: ImageView = itemView.findViewById(R.id.whap_icon)
        val vbrShare: ImageView = itemView.findViewById(R.id.vbr_icon)
        val linShare: ImageView = itemView.findViewById(R.id.lin_icon)
        val copy: ImageView = itemView.findViewById(R.id.copy_icon)

        fun updateView(context: Context, q:Quote) {
            quote.text = "\"${q.quote}\""
            author.text = q.author
            series.text = q.series

            fbShare.setOnClickListener { CommonUtils().shareSocial(context, "com.facebook.katana", q) }
            msgShare.setOnClickListener { CommonUtils().shareSocial(context, "com.facebook.orca", q) }
            twtShare.setOnClickListener { CommonUtils().shareSocial(context, "com.twitter.android", q) }
            whapShare.setOnClickListener { CommonUtils().shareSocial(context, "com.whatsapp", q) }
            vbrShare.setOnClickListener { CommonUtils().shareSocial(context, "com.viber.voip", q) }
            linShare.setOnClickListener { CommonUtils().shareSocial(context, "com.linkedin.android", q) }
            copy.setOnClickListener { CommonUtils().copyQuote(context, q) }
        }
    }
}