package com.jung.ktdemo.ui.mergeadaptere.holder

import android.annotation.SuppressLint
import android.os.Build.VERSION.SDK_INT
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.api.load
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
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

    /***
     * 目前 GIF 動不了
     */
    private val imageLoader by lazy {
        ImageLoader.Builder(view.context)
            .componentRegistry {
                if (SDK_INT >= 28) {
                    add(ImageDecoderDecoder())
                } else {
                    add(GifDecoder())
                }
            }
            .build()
    }

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

        /***
         * @crossfade 視覺效果，使圖片看起来像淡入淡出
         */
        binding.image.load(engineer.image, imageLoader) {
            crossfade(true)
            placeholder(R.drawable.ic_launcher_foreground)
            val dp = dpToPx(view.context, 5)
            transformations(RoundedCornersTransformation(dp))
        }
    }
}