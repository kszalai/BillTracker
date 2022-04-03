package com.kszalai.billtracker.bills.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kszalai.billtracker.databinding.BillDetailFragmentBinding
import com.kszalai.billtracker.models.BillObject
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BillDetailFragment : Fragment() {
    private val viewModel: BillDetailViewModel by viewModels()
    lateinit var binding: BillDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BillDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.get("selectedBill")?.let {
            viewModel.setBill(it as BillObject)
        }
        binding.root.setContent {
            BillDetails(viewModel = viewModel)
        }
    }
}
