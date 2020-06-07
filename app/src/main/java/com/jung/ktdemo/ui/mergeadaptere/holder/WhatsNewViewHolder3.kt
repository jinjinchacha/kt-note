package com.jung.ktdemo.ui.mergeadaptere.holder

import android.view.View
import com.jung.ktdemo.databinding.ItemWhatsNew3Binding
import com.jung.ktdemo.ui.base.BaseViewHolder
import com.jung.ktdemo.ui.mergeadaptere.ClickListener
import com.jung.ktdemo.ui.mergeadaptere.wrapper.WhatsNewWrapper

class WhatsNewViewHolder3(val view: View,
                          private val listener: ClickListener
) : BaseViewHolder<WhatsNewWrapper>(view) {
    private var _binding: ItemWhatsNew3Binding? = null
    private val binding get() = _binding!!

    init {
        _binding = ItemWhatsNew3Binding.bind(view)
    }

    override fun bindView(position: Int, t: WhatsNewWrapper) {
        binding.whatsNew.text = t.description
        binding.tvClick.setOnClickListener {
            listener.onClick(absoluteAdapterPosition)
        }
    }
}