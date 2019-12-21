package com.kszalai.billtracker.ui.bills

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.kszalai.billtracker.R

class BillListFragment : Fragment() {

    companion object {
        fun newInstance() = BillListFragment()
    }

    private lateinit var viewModel: BillListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bill_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BillListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
