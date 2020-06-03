package com.jung.ktdemo.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jung.ktdemo.R
import com.jung.ktdemo.databinding.SubjectItemBinding

class MainAdapter :
    ListAdapter<Pair<String, NavDirections>, MainAdapter.ViewHolder>(ItemDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.subject_item,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding.subject) {
            text = item.first
            setOnClickListener { findNavController(holder.itemView).navigate(item.second) }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var _binding: SubjectItemBinding? = null
        val binding get() = _binding!!

        init {
            _binding = SubjectItemBinding.bind(itemView)
        }
    }

    private class ItemDiffCallBack : DiffUtil.ItemCallback<Pair<String, NavDirections>>() {
        override fun areItemsTheSame(
            o: Pair<String, NavDirections>,
            n: Pair<String, NavDirections>
        ) = o == n

        override fun areContentsTheSame(
            o: Pair<String, NavDirections>,
            n: Pair<String, NavDirections>
        ) = o == n
    }
}
