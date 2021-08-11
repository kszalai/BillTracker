package com.kszalai.billtracker.ui.viewholders

import com.kszalai.billtracker.databinding.DetailRvPaymentHistItemBinding
import com.kszalai.billtracker.helpers.formatToCurrency
import com.kszalai.billtracker.models.BillPayment

class DetailHistoryViewHolder(override val viewBinding: DetailRvPaymentHistItemBinding) : BaseViewHolder<BillPayment>(viewBinding) {
    override fun bind(item: BillPayment) {
        viewBinding.paymentHistoryDate.text = item.paymentDate
        viewBinding.paymentAmount.text = item.amount.formatToCurrency()
    }
}