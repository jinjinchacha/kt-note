package com.jung.ktdemo.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.jung.ktdemo.databinding.MainFragmentBinding
import com.jung.ktdemo.ui.base.BaseFragment

class MainFragment : BaseFragment<MainFragmentBinding>() {

    override fun bindView(inflater: LayoutInflater, container: ViewGroup?): MainFragmentBinding =
        MainFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val subjectList = listOf(
            Pair("Coroutine", MainFragmentDirections.actionMainFragmentToCoroutineFragment())
        )

        with(binding.recycler) {
            layoutManager = LinearLayoutManager(context)
            adapter = MainAdapter()
            (adapter as MainAdapter).submitList(subjectList)
        }
    }
}