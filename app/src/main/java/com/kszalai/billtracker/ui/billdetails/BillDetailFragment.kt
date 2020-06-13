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
import com.kszalai.billtracker.DI.Helpers.Injectable

import com.kszalai.billtracker.R
import com.kszalai.billtracker.databinding.BillDetailFragmentBinding
import com.kszalai.billtracker.helpers.determineColorFromDate
import com.kszalai.billtracker.helpers.formatToCurrency
import com.kszalai.billtracker.helpers.getIcon
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
        binding?.viewmodel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
    }

    private fun setObservers() {
        viewModel.billRepo.selectedBill.observe(viewLifecycleOwner, Observer {
            billDetailName.text = it.billName
            billDetailAmountDue.text = it.amount.formatToCurrency()
            billDetailDueOn.text = it.dueDate
            billDetailDueInfoCardView.setCardBackgroundColor(resources.getColor(it.dueDate.determineColorFromDate()))
            billDetailAmountPaid.text = it.amountPaid.formatToCurrency()
            billDetailPaidOn.text = it.datePaid
            billDetailImage.setImageResource(it.billType.getIcon())
        })
    }
}
