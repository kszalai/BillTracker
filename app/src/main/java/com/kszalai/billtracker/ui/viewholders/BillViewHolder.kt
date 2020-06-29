package com.kszalai.billtracker.ui.viewholders

import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.kszalai.billtracker.R
import com.kszalai.billtracker.helpers.determineColorFromDate
import com.kszalai.billtracker.helpers.formatToCurrency
import com.kszalai.billtracker.helpers.getIcon
import com.kszalai.billtracker.models.BillObject
import com.kszalai.billtracker.repo.BillRepo
import kotlinx.android.synthetic.main.bill_item_layout.view.*

class BillViewHolder(itemView: View, private val navController: NavController, private val billRepo: BillRepo) : BaseViewHolder<BillObject>(itemView) {

    override fun bind(item: BillObject) {
        itemView.billName.text = item.billName
        itemView.amountDue.text = item.amount.formatToCurrency()
        itemView.dueDate.text = item.dueDate
        itemView.billCardView.setOnClickListener {
            val bundle = bundleOf("selectedBill" to item)
            navController.navigate(R.id.action_billListFragment_to_billDetailFragment, bundle)
        }

        itemView.billLogo.setImageResource(item.billType.getIcon())

        item.balance?.let {
            itemView.balanceRemaining.visibility = View.VISIBLE
            itemView.balanceText.text = item.balance.formatToCurrency()
        } ?: run {
            itemView.balanceRemaining.visibility = View.GONE
        }

        itemView.billCardView.setCardBackgroundColor(itemView.resources.getColor(item.dueDate.determineColorFromDate()))
    }

}