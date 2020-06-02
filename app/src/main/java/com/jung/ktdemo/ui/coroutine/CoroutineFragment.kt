package com.jung.ktdemo.ui.coroutine

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jung.ktdemo.databinding.CoroutineFragmentBinding
import com.jung.ktdemo.ui.base.BaseFragment

class CoroutineFragment : BaseFragment<CoroutineFragmentBinding>() {

    override fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): CoroutineFragmentBinding = CoroutineFragmentBinding.inflate(inflater, container, false)
}