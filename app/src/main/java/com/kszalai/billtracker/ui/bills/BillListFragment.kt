package com.kszalai.billtracker.ui.bills

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kszalai.billtracker.databinding.BillListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BillListFragment : Fragment() {

    companion object {
        fun newInstance() = BillListFragment()
    }

    private val viewModel: BillListViewModel by viewModels()

    lateinit var binding: BillListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BillListFragmentBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        setObservers()
    }

    private fun setObservers() {
        viewModel.bills.observe(viewLifecycleOwner, Observer {
            val adapter = binding.billListRecyclerView.adapter as BillListRecyclerViewAdapter
            adapter.setData(it)
        })
    }

    private fun setAdapter() {
        binding.billListRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.billListRecyclerView.adapter = BillListRecyclerViewAdapter(
            navController = findNavController(),
            billRepo = viewModel.billRepo
        )
    }
}
