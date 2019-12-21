package com.kszalai.billtracker.ui.billdetails

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.kszalai.billtracker.R

class BillDetailFragment : Fragment() {

    companion object {
        fun newInstance() = BillDetailFragment()
    }

    private lateinit var viewModel: BillDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bill_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BillDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
