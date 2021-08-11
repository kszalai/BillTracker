package com.kszalai.billtracker.ui.viewholders

import com.kszalai.billtracker.databinding.DetailRvCreditInfoBinding
import com.kszalai.billtracker.helpers.formatToCurrency
import com.kszalai.billtracker.helpers.formatToPercentage
import com.kszalai.billtracker.models.CreditCardLimit

class DetailCreditViewHolder(override val viewBinding: DetailRvCreditInfoBinding) : BaseViewHolder<CreditCardLimit>(viewBinding) {
    override fun bind(item: CreditCardLimit) {
        viewBinding.detailCreditLimitValue.text = item.creditLimit.formatToCurrency()
        viewBinding.detailCreditAprValue.text = item.apr.formatToPercentage()
    }
}