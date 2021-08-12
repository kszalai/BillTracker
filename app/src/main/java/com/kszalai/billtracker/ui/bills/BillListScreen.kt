package com.kszalai.billtracker.ui.bills

import android.content.res.Configuration
import android.os.Bundle
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kszalai.billtracker.models.BillObject
import com.kszalai.billtracker.models.SampleBillObjectList
import com.kszalai.billtracker.ui.viewholders.BillOverview

@ExperimentalMaterialApi
@Composable
fun BillListScreen(
    bills: List<BillObject>,
    onNavigate: (Int, Bundle) -> Unit
) {
    LazyColumn {
        items(bills) { bill ->
            BillOverview(data = bill, onNavigate = onNavigate)
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
        bills = SampleBillObjectList.data,
        onNavigate = { data, nav -> }
    )
}