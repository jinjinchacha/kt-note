package com.jung.ktdemo.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jung.ktdemo.R
import com.jung.ktdemo.databinding.SubjectItemBinding

class MainAdapter(private var listener: OnClickEvent) :
    ListAdapter<String, MainAdapter.ViewHolder>(ItemDiffCallBack()) {
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
            text = item
            setOnClickListener { listener.navigate(item) }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var _binding: SubjectItemBinding? = null
        val binding get() = _binding!!

        init {
            _binding = SubjectItemBinding.bind(itemView)
        }
    }

    private class ItemDiffCallBack : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(o: String, n: String) = o == n

        override fun areContentsTheSame(o: String, n: String) = o == n
    }
}
