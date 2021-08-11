package com.kszalai.billtracker.ui.billdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kszalai.billtracker.R
import com.kszalai.billtracker.helpers.formatToCurrency
import com.kszalai.billtracker.helpers.formatToPercentage
import com.kszalai.billtracker.models.BillDetailRecyclerViewItems
import com.kszalai.billtracker.models.BillPayment
import com.kszalai.billtracker.models.CreditCardLimit
import kotlinx.android.synthetic.main.detail_rv_credit_info.view.*
import kotlinx.android.synthetic.main.detail_rv_payment_hist_item.view.*
import java.lang.Exception
import java.lang.IllegalArgumentException

class BillDetailRecyclerViewAdapter(private var items: ArrayList<Any> = arrayListOf()) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val paymentHistoryHeader = 1
        private const val paymentHistoryItem = 2
        private const val creditLimitItem = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = when(viewType) {
            paymentHistoryHeader -> {
                LayoutInflater.from(parent.context).inflate(R.layout.detail_rv_payment_hist_header, parent, false)
            }
            paymentHistoryItem -> {
                LayoutInflater.from(parent.context).inflate(R.layout.detail_rv_payment_hist_item, parent, false)
            }
            creditLimitItem -> {
                LayoutInflater.from(parent.context).inflate(R.layout.detail_rv_credit_info, parent, false)
            }
            else -> {
                throw Exception("Invalid viewType received")
            }
        }

        return object : RecyclerView.ViewHolder(view) { }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            paymentHistoryItem -> {
                val data = items[position] as BillPayment
                holder.itemView.paymentHistoryDate.text = data.paymentDate
                holder.itemView.paymentAmount.text = data.amount.formatToCurrency()
            }
            creditLimitItem -> {
                val data = items[position] as CreditCardLimit
                holder.itemView.detailCreditLimitValue.text = data.creditLimit.formatToCurrency()
                holder.itemView.detailCreditAprValue.text = data.apr.formatToPercentage()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(items[position]) {
            BillDetailRecyclerViewItems.PaymentHeader -> paymentHistoryHeader
            is BillPayment -> paymentHistoryItem
            is CreditCardLimit -> creditLimitItem
            else -> throw IllegalArgumentException("Invalid item received!")
        }
    }

    fun setData(data: ArrayList<Any>) {
        items = data
        notifyDataSetChanged()
    }
}