package com.jung.ktdemo.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T : ViewBinding> : Fragment() {
    private var _binding: T? = null
    protected val binding get() = _binding!!

    protected abstract fun getToolbar(): Toolbar

    abstract fun bindView(inflater: LayoutInflater, container: ViewGroup?): T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = bindView(inflater, container).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getToolbar().setupWithNavController(findNavController())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}