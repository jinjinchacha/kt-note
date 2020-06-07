package com.jung.ktdemo.ui.mergeadaptere.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jung.ktdemo.R
import com.jung.ktdemo.ui.mergeadaptere.ClickListener
import com.jung.ktdemo.ui.mergeadaptere.holder.EngineerViewHolder
import com.jung.ktdemo.ui.mergeadaptere.model.Engineer

class EngineersAdapter(private val listener: ClickListener) :
    RecyclerView.Adapter<EngineerViewHolder>() {

    var engineers: List<Engineer> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EngineerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_engineer, parent, false)
        return EngineerViewHolder(view, listener)
    }

    override fun getItemViewType(position: Int): Int = R.layout.item_engineer


    override fun getItemCount(): Int = engineers.size

    override fun onBindViewHolder(holder: EngineerViewHolder, position: Int) {
        holder.bind(engineers[position])
    }
}