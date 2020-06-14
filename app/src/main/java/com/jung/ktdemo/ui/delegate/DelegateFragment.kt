package com.jung.ktdemo.ui.delegate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jung.ktdemo.databinding.DelegateFragmentBinding
import com.jung.ktdemo.ui.base.BaseFragment

class DelegateFragment : BaseFragment<DelegateFragmentBinding>() {

    override fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): DelegateFragmentBinding {
        return DelegateFragmentBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val b = BaseImpl("A")
        Derived(b).printA() // 輸出 A


        val e = Example()
        e.s="Hello world"
        println(e.s)
    }

}