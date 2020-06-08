package com.kszalai.billtracker.ui.viewholders

import android.view.View
import com.kszalai.billtracker.R
import com.kszalai.billtracker.helpers.formatToCurrency
import com.kszalai.billtracker.helpers.toast
import com.kszalai.billtracker.models.BillObject
import com.kszalai.billtracker.models.BillType
import kotlinx.android.synthetic.main.bill_item_layout.view.*

class BillViewHolder(itemView: View) : BaseViewHolder<BillObject>(itemView) {

    override fun bind(item: BillObject) {
        itemView.billName.text = item.billName
        itemView.amountDue.text = item.amount.formatToCurrency()
        itemView.dueDate.text = item.dueDate
        itemView.billCardView.setOnClickListener {
            "You tapped ${item.billName}".toast(itemView.context)
        }

        when (item.billType) {
            BillType.CreditCard -> {
                itemView.billLogo.setImageResource(R.drawable.credit_card_icon)
            }
            BillType.Mortgage -> {
                itemView.billLogo.setImageResource(R.drawable.mortgage_icon)
            }
            BillType.Subscription -> {
                itemView.billLogo.setImageResource(R.drawable.subscription_icon)
            }
            BillType.Utility -> {
                itemView.billLogo.setImageResource(R.drawable.bill_icon)
            }
            BillType.Auto -> {
                itemView.billLogo.setImageResource(R.drawable.auto_icon)
            }
        }

        if (item.balance != null) {
            itemView.balanceRemaining.visibility = View.VISIBLE
            itemView.balanceText.text = item.balance.formatToCurrency()
        }

        itemView.billCardView.setCardBackgroundColor(itemView.resources.getColor(R.color.backgroundFarOut))
    }

}