package com.kszalai.billtracker.ui.viewholders

import android.view.View
import androidx.navigation.NavController
import com.kszalai.billtracker.R
import com.kszalai.billtracker.helpers.determineColorFromDate
import com.kszalai.billtracker.helpers.formatToCurrency
import com.kszalai.billtracker.helpers.getIcon
import com.kszalai.billtracker.helpers.toast
import com.kszalai.billtracker.models.BillObject
import com.kszalai.billtracker.models.BillType
import com.kszalai.billtracker.repo.BillRepo
import kotlinx.android.synthetic.main.bill_item_layout.view.*

class BillViewHolder(itemView: View, val navController: NavController, val billRepo: BillRepo) : BaseViewHolder<BillObject>(itemView) {

    override fun bind(item: BillObject) {
        itemView.billName.text = item.billName
        itemView.amountDue.text = item.amount.formatToCurrency()
        itemView.dueDate.text = item.dueDate
        itemView.billCardView.setOnClickListener {
            billRepo.setSelectedBill(item)
            navController.navigate(R.id.action_billListFragment_to_billDetailFragment)
        }

        itemView.billLogo.setImageResource(item.billType.getIcon())

        if (item.balance != null) {
            itemView.balanceRemaining.visibility = View.VISIBLE
            itemView.balanceText.text = item.balance.formatToCurrency()
        }
        else {
            itemView.balanceRemaining.visibility = View.GONE
        }

        itemView.billCardView.setCardBackgroundColor(itemView.resources.getColor(item.dueDate.determineColorFromDate()))
    }

}