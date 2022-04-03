package com.kszalai.billtracker.bills

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kszalai.billtracker.R
import com.kszalai.billtracker.bills.common.repo.BillRepo
import com.kszalai.billtracker.bills.common.services.BillService
import com.kszalai.billtracker.databinding.BillListFragmentBinding
import com.kszalai.billtracker.models.BillObject
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
        return binding.root
    }

    @OptIn(ExperimentalMaterialApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
    }

    @ExperimentalMaterialApi
    private fun setObservers() {
        viewModel.bills.observe(viewLifecycleOwner) {
            binding.billListComposeView.setContent {
                BillListScreen(
                    viewModel = viewModel,
                    onBillItemNavigate = { dest, bundle ->
                        findNavController().navigate(dest, bundle)
                    },
                    onAddBillNavigate = { dest, bundle ->
                        findNavController().navigate(dest, bundle)
                    }
                )
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun BillListScreen(
    viewModel: BillListViewModel,
    onBillItemNavigate: (Int, Bundle) -> Unit,
    onAddBillNavigate: (Int, Bundle?) -> Unit
) {
    val bills: List<BillObject> by viewModel.bills.observeAsState(listOf())

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(bills) { bill ->
                BillOverview(data = bill, onNavigate = onBillItemNavigate)
            }
        }
        FloatingActionButton(
            onClick = {
                onAddBillNavigate(R.id.action_billListFragment_to_addBillFragment, null)
            },
            modifier = Modifier
                .padding(all = 16.dp)
                .align(Alignment.BottomEnd)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add Bill"
            )
        }
    }
}

@ExperimentalMaterialApi
@Preview(name = "Light Mode")
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PreviewBillListScreen() {
    BillListScreen(
        viewModel = BillListViewModel(billRepo = BillRepo(billService = BillService())),
        onBillItemNavigate = { data, nav -> },
        onAddBillNavigate = { data, nav -> }
    )
}