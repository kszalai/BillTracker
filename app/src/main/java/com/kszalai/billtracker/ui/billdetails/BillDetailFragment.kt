package com.kszalai.billtracker.ui.billdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.kszalai.billtracker.DI.Helpers.Injectable

import com.kszalai.billtracker.R
import com.kszalai.billtracker.databinding.BillDetailFragmentBinding
import com.kszalai.billtracker.helpers.determineColorFromDate
import com.kszalai.billtracker.helpers.formatToCurrency
import com.kszalai.billtracker.helpers.getIcon
import com.kszalai.billtracker.models.BillObject
import kotlinx.android.synthetic.main.bill_detail_fragment.*
import javax.inject.Inject

class BillDetailFragment : Fragment(), Injectable {

    companion object {
        fun newInstance() = BillDetailFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: BillDetailViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: BillDetailFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.bill_detail_fragment, container, false)
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

        billDetailPaymentRecyclerView.apply {
            adapter = billDetailRecyclerViewAdapter
            layoutManager = llm
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun setObservers() {
        viewModel.detailItems.observe(viewLifecycleOwner, Observer {
            val adapter = billDetailPaymentRecyclerView.adapter as BillDetailRecyclerViewAdapter
            adapter.setData(it)
        })

        viewModel.lastPaidVisibility.observe(viewLifecycleOwner, Observer {
            billDetailPaidInfoCardView.visibility = it
        })
    }
}
