package com.kszalai.billtracker.ui.bills

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.kszalai.billtracker.DI.Helpers.Injectable

import com.kszalai.billtracker.R
import com.kszalai.billtracker.databinding.BillListFragmentBinding
import javax.inject.Inject

class BillListFragment : Fragment(), Injectable {

    companion object {
        fun newInstance() = BillListFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: BillListViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: BillListFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.bill_list_fragment, container, false)
        binding?.viewmodel = viewModel

        return binding.root
    }
}
