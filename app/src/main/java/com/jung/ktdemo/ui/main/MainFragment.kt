package com.jung.ktdemo.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jung.ktdemo.databinding.MainFragmentBinding
import com.jung.ktdemo.ui.base.BaseFragment

class MainFragment : BaseFragment<MainFragmentBinding>(), OnClickEvent {

    override fun getToolbar() = binding.toolbar

    override fun bindView(inflater: LayoutInflater, container: ViewGroup?): MainFragmentBinding =
        MainFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val subjectList = listOf(
            "Coroutine"
        )

        with(binding.recycler) {
            layoutManager = LinearLayoutManager(context)
            adapter = MainAdapter(this@MainFragment)
            (adapter as MainAdapter).submitList(subjectList)
        }
    }

    override fun navigate(subject: String) {
        when (subject) {
            "Coroutine" -> findNavController().navigate(MainFragmentDirections.actionMainFragmentToCoroutineFragment())
        }
    }
}