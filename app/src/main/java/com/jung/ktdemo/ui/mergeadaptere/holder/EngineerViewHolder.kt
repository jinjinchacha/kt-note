package com.jung.ktdemo.ui.mergeadaptere.holder

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.google.android.material.internal.ViewUtils.dpToPx
import com.jung.ktdemo.R
import com.jung.ktdemo.databinding.ItemEngineerBinding
import com.jung.ktdemo.ui.mergeadaptere.ClickListener
import com.jung.ktdemo.ui.mergeadaptere.model.Engineer

class EngineerViewHolder(private val view: View,
                         private val listener: ClickListener
) : RecyclerView.ViewHolder(view) {
    private var _binding: ItemEngineerBinding? = null
    private val binding get() = _binding!!

    init {
        _binding = ItemEngineerBinding.bind(view)
    }

    @SuppressLint("RestrictedApi")
    fun bind(engineer: Engineer) {
        binding.itemBg.setOnClickListener {
            listener.onClick(bindingAdapterPosition)
        }
        binding.name.text = engineer.name
        binding.role.text = engineer.role
        binding.image.load(engineer.image) {
            crossfade(true)
            placeholder(R.drawable.ic_launcher_foreground)
            val dp = dpToPx(view.context, 5)
            transformations(RoundedCornersTransformation(dp))
        }
    }
}