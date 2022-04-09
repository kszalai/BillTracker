package com.kszalai.billtracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kszalai.billtracker.bills.BillListScreen
import com.kszalai.billtracker.bills.BillListViewModel
import com.kszalai.billtracker.bills.add.AddBillScreen
import com.kszalai.billtracker.bills.add.AddBillViewModel
import com.kszalai.billtracker.bills.details.BillDetailViewModel
import com.kszalai.billtracker.bills.details.BillDetails
import com.kszalai.billtracker.common.theme.BillTrackerColors
import com.kszalai.billtracker.common.theme.BillTrackerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BillTracker()
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun BillTracker() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    BillTrackerTheme {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    title = { Text("Bill Tracker") },
                    contentColor = Color.White
                )
            },
            backgroundColor = BillTrackerColors.Background,
            contentColor = BillTrackerColors.OnBackground
        ) {
            NavHost(
                navController = navController,
                startDestination = "overview",
            ) {
                composable("overview") {
                    val vm = hiltViewModel<BillListViewModel>()
                    BillListScreen(
                        viewModel = vm,
                        navController = navController
                    )
                }
                composable(
                    route = "details/{billId}",
                    arguments = listOf(
                        navArgument("billId") {
                            type = NavType.IntType
                        }
                    )
                ) {
                    val vm = hiltViewModel<BillDetailViewModel>()
                    val billId = it.arguments?.getInt("billId")
                    vm.setBill(data = billId)
                    BillDetails(
                        viewModel = vm
                    )
                }
                composable("add") {
                    val vm = hiltViewModel<AddBillViewModel>()
                    AddBillScreen(
                        viewModel = vm
                    )
                }
            }
        }
    }
}