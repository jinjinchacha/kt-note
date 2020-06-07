package com.jung.ktdemo.ui.mergeadaptere

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.MergeAdapter
import com.jung.ktdemo.databinding.MergeadapterFragmentBinding
import com.jung.ktdemo.ui.base.BaseFragment
import com.jung.ktdemo.ui.mergeadaptere.adapter.EngineersAdapter
import com.jung.ktdemo.ui.mergeadaptere.adapter.WhatsNewAdapter
import com.jung.ktdemo.ui.mergeadaptere.model.getEngineers
import com.jung.ktdemo.ui.mergeadaptere.model.getWhatsNew
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MergeAdapterFragment : BaseFragment<MergeadapterFragmentBinding>(), ClickListener {

    private val whatsNewAdapter: WhatsNewAdapter by lazy {
        WhatsNewAdapter(
            this
        )
    }
    private val engineerAdapter: EngineersAdapter by lazy {
        EngineersAdapter(
            this
        )
    }
    private lateinit var mergeAdapter: MergeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val config = MergeAdapter.Config.Builder()
            .setIsolateViewTypes(false)
            .build()

        mergeAdapter = MergeAdapter(config, whatsNewAdapter, engineerAdapter)
        binding.rvEngineer.adapter = mergeAdapter
        fetchNewFeatureDataFromServer()
        fetchEngineer()
    }

    private fun fetchEngineer() {

        val listOfEngineers = getEngineers().sortedBy { it.name }
        engineerAdapter.engineers = listOfEngineers
        engineerAdapter.notifyItemRangeInserted(0, listOfEngineers.size)
        binding.rvEngineer.scrollToPosition(0)
    }

    private fun fetchNewFeatureDataFromServer() {

        /**
         * Faking a long running network call.
         * This is a contrived example. Ideally this would be in a repository class
         * */
        lifecycleScope.launch {
            delay(2_000L)
            val list = getWhatsNew()
            whatsNewAdapter.whatsNew = list
            whatsNewAdapter.notifyItemRangeInserted(0, list.size)
            binding.rvEngineer.scrollToPosition(0)
        }
    }

    override fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): MergeadapterFragmentBinding = MergeadapterFragmentBinding.inflate(inflater, container, false)

    override fun onClick(position: Int) {
        Toast.makeText(context, "click:$position", Toast.LENGTH_SHORT).show()
    }
}