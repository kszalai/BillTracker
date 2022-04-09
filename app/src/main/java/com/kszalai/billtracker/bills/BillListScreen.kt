package com.kszalai.billtracker.bills

import android.content.res.Configuration
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.kszalai.billtracker.bills.common.models.BillObject
import com.kszalai.billtracker.bills.common.models.SampleBillObjectList

@ExperimentalMaterialApi
@Composable
fun BillListScreen(
    viewModel: BillListViewModel = viewModel(),
    navController: NavController
) {
    val bills: List<BillObject> by viewModel.bills.observeAsState(listOf())
    BillListScreen(
        data = bills,
        onBillItemNavigate = { route ->
            navController.navigate(route)
        },
        onAddBillNavigate = { route ->
            navController.navigate(route)
        }
    )
}

@ExperimentalMaterialApi
@Composable
private fun BillListScreen(
    data: List<BillObject>,
    onBillItemNavigate: (String) -> Unit,
    onAddBillNavigate: (String) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
        ) {
            items(data) { bill ->
                BillOverview(
                    data = bill,
                    onNavigate = onBillItemNavigate
                )
            }
        }
        FloatingActionButton(
            onClick = {
                onAddBillNavigate("add")
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
private fun PreviewBillListScreen() {
    BillListScreen(
        data = SampleBillObjectList.data,
        onAddBillNavigate = { },
        onBillItemNavigate = { }
    )
}