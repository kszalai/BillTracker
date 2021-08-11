package com.kszalai.billtracker.ui.billdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.kszalai.billtracker.databinding.BillDetailFragmentBinding
import com.kszalai.billtracker.models.BillObject
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BillDetailFragment : Fragment() {

    companion object {
        fun newInstance() = BillDetailFragment()
    }

    private val viewModel: BillDetailViewModel by viewModels()
    lateinit var binding: BillDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BillDetailFragmentBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.get("selectedBill")?.let {
            viewModel.setBill(it as BillObject)
        }
        setupRecyclerView()
        setObservers()
    }

    private fun setupRecyclerView() {
        val billDetailRecyclerViewAdapter = BillDetailRecyclerViewAdapter()
        val llm = LinearLayoutManager(context)

        binding.billDetailPaymentRecyclerView.apply {
            adapter = billDetailRecyclerViewAdapter
            layoutManager = llm
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun setObservers() {
        viewModel.detailItems.observe(viewLifecycleOwner, Observer {
            val adapter =
                binding.billDetailPaymentRecyclerView.adapter as BillDetailRecyclerViewAdapter
            adapter.setData(it)
        })

        viewModel.lastPaidVisibility.observe(viewLifecycleOwner, Observer {
            binding.billDetailPaidInfoCardView.visibility = it
        })
    }
}
