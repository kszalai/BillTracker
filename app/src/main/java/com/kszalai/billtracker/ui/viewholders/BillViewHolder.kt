package com.kszalai.billtracker.ui.viewholders

import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.kszalai.billtracker.R
import com.kszalai.billtracker.databinding.BillItemLayoutBinding
import com.kszalai.billtracker.helpers.determineColorFromDate
import com.kszalai.billtracker.helpers.formatToCurrency
import com.kszalai.billtracker.helpers.getIcon
import com.kszalai.billtracker.models.BillObject
import com.kszalai.billtracker.repo.BillRepo

class BillViewHolder(override val viewBinding: BillItemLayoutBinding, private val navController: NavController, private val billRepo: BillRepo) : BaseViewHolder<BillObject>(viewBinding) {

    override fun bind(item: BillObject) {
        viewBinding.billName.text = item.billName
        viewBinding.amountDue.text = item.nextPayment.amount.formatToCurrency()
        viewBinding.dueDate.text = item.nextPayment.paymentDate
        viewBinding.billCardView.setOnClickListener {
            val bundle = bundleOf("selectedBill" to item)
            navController.navigate(R.id.action_billListFragment_to_billDetailFragment, bundle)
        }

        viewBinding.billLogo.setImageResource(item.billType.getIcon())

        item.balance?.let {
            viewBinding.balanceRemaining.visibility = View.VISIBLE
            viewBinding.balanceText.text = item.balance.formatToCurrency()
        } ?: run {
            viewBinding.balanceRemaining.visibility = View.GONE
        }

        viewBinding.billCardView.setCardBackgroundColor(ResourcesCompat.getColor(viewBinding.root.context.resources, item.nextPayment.paymentDate.determineColorFromDate(), null))
    }

}