package com.appwiz.breakingbad.ui

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appwiz.breakingbad.R
import com.appwiz.breakingbad.adapter.QuotesAdapter
import com.appwiz.breakingbad.utils.NetworkState
import com.appwiz.breakingbad.viewmodel.QuotesViewModel

class QuotesFragment:Fragment() {
    lateinit var viewmodel: QuotesViewModel
    lateinit var adapter: QuotesAdapter
    private lateinit var loader: ProgressBar
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_quotes, container, false)
        loader = view.findViewById(R.id.loader)
        recyclerView = view.findViewById(R.id.quotesRV)
        viewmodel = ViewModelProvider(this).get(QuotesViewModel::class.java)
        viewmodel.loadData()

        viewmodel.state.observe(viewLifecycleOwner, Observer {
            when (it) {
                NetworkState.LOADING -> {
                    loader.visibility = View.VISIBLE
                    // error.visibility = View.GONE
                }
                NetworkState.LOADED -> {
                    loader.visibility = View.GONE
                }
                else -> {
                    loader.visibility = View.GONE
                    recyclerView.visibility = View.GONE
                    // error.visibility = View.VISIBLE
                }
            }
        })

        adapter = context?.let { QuotesAdapter(it, emptyList()) }!!
        viewmodel.quotes.observe(viewLifecycleOwner, Observer { adapter.setData(it) })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        val decoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        decoration.setDrawable(ColorDrawable(resources.getColor(R.color.colorPrimaryText)))
        recyclerView.addItemDecoration(decoration)
        return view
    }
}