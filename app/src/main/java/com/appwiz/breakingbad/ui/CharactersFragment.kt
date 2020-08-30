package com.appwiz.breakingbad.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appwiz.breakingbad.R
import com.appwiz.breakingbad.adapter.CharactersAdapter
import com.appwiz.breakingbad.utils.NetworkState
import com.appwiz.breakingbad.viewmodel.CharactersViewModel

class CharactersFragment:Fragment() {
    lateinit var viewmodel: CharactersViewModel
    lateinit var adapter: CharactersAdapter
    private lateinit var loader: ProgressBar
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_characters, container, false)
        loader = view.findViewById(R.id.loader)
        recyclerView = view.findViewById(R.id.charsRV)
        viewmodel = ViewModelProvider(this).get(CharactersViewModel::class.java)
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
        adapter = context?.let { CharactersAdapter(it, emptyList()) }!!
        viewmodel.characters.observe(viewLifecycleOwner, Observer { adapter.setData(it) })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        return view
    }
}