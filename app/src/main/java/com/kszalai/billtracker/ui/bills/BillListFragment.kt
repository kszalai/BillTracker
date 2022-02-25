package com.kszalai.billtracker.ui.bills

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.ExperimentalMaterialApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kszalai.billtracker.databinding.BillListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BillListFragment : Fragment() {

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

    @ExperimentalMaterialApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
    }

    @ExperimentalMaterialApi
    private fun setObservers() {
        viewModel.bills.observe(viewLifecycleOwner, Observer {
            binding.billListComposeView.setContent {
                BillListScreen(
                    bills = it,
                    onNavigate = { dest, bundle ->
                        findNavController().navigate(dest, bundle)
                    }
                )
            }
        })
    }
}
