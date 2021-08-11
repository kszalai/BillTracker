package com.kszalai.billtracker.ui.billdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kszalai.billtracker.databinding.DetailRvCreditInfoBinding
import com.kszalai.billtracker.databinding.DetailRvPaymentHistHeaderBinding
import com.kszalai.billtracker.databinding.DetailRvPaymentHistItemBinding
import com.kszalai.billtracker.models.BillDetailRecyclerViewItems
import com.kszalai.billtracker.models.BillPayment
import com.kszalai.billtracker.models.CreditCardLimit
import com.kszalai.billtracker.ui.viewholders.DetailCreditViewHolder
import com.kszalai.billtracker.ui.viewholders.DetailHistoryViewHolder

class BillDetailRecyclerViewAdapter(private var items: ArrayList<Any> = arrayListOf()) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val paymentHistoryHeader = 1
        private const val paymentHistoryItem = 2
        private const val creditLimitItem = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            paymentHistoryHeader -> {
                val binding = DetailRvPaymentHistHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                object : RecyclerView.ViewHolder(binding.root) { }
            }
            paymentHistoryItem -> {
                val binding = DetailRvPaymentHistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                DetailHistoryViewHolder(binding)
            }
            creditLimitItem -> {
                val binding = DetailRvCreditInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                DetailCreditViewHolder(binding)
            }
            else -> {
                throw Exception("Invalid viewType received")
            }
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            paymentHistoryItem -> {
                (holder as DetailHistoryViewHolder).bind(items[position] as BillPayment)
            }
            creditLimitItem -> {
                (holder as DetailCreditViewHolder).bind(items[position] as CreditCardLimit)
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